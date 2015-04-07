package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sourceServlet.SourceServletAjouterVote;
import baseDeDonnees.Bdd;

public class ServletAjouterVote extends HttpServlet  {

	private static final long serialVersionUID = 1438952108260051375L;
	private Bdd bdd;
	
	public ServletAjouterVote (){
		this.bdd = new Bdd();
		this.bdd.connexionBdd();
	}
	
	protected void finalize(){
		this.bdd.closeConnexion();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<String> valeurs = new ArrayList<>();
		valeurs.add(session.getAttribute("identifiantSession").toString());
		valeurs.add(session.getAttribute("identifiantEtudiant").toString());
		ArrayList<String> typeValeurs = new ArrayList<>();
		typeValeurs.add("int");
		typeValeurs.add("int");
		ResultSet resultat = bdd.faireSelectParam("select * from vote where ID_Session = ? and ID_Etudiant = ? ;", valeurs, typeValeurs);
		try {
			typeValeurs.add("int");
			if(!resultat.next()){
				int id = SourceServletAjouterVote.creerIdSession(bdd);
				valeurs.add(request.getParameter("valeurVote"));
				valeurs.add(String.valueOf(id));
				typeValeurs.add("int");
				bdd.faireInsert("insert into vote (ID_Session,ID_Etudiant, valeur,ID_Vote) values ( ?, ?, ?, ? ); ", valeurs, typeValeurs);
			}else{
				valeurs.add(0,"4");
				bdd.faireInsert("update vote set valeur = ? where ID_Session = ? and ID_Etudiant = ? ;",valeurs, typeValeurs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/voteEtudiant.jsp").forward(request, response);
	}
	
}
