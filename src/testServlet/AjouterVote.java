package testServlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import queryServlet.Bdd;

public class AjouterVote extends HttpServlet  {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Bdd bdd = new Bdd();
		bdd.connexionBdd();
		ArrayList<String> valeurs = new ArrayList<>();
		valeurs.add(session.getAttribute("identifiantSession").toString());
		valeurs.add(session.getAttribute("identifiantEtudiant").toString());
		System.out.println("idEtu : "+session.getAttribute("identifiantEtudiant")+ " et idSess : "+session.getAttribute("identifiantSession"));
		ArrayList<String> typeValeurs = new ArrayList<>();
		typeValeurs.add("int");
		typeValeurs.add("int");
		ResultSet resultat = bdd.faireSelectParam("select * from vote where ID_Session = ? and ID_Etudiant = ? ;", valeurs, typeValeurs);
		try {
			typeValeurs.add("int");
			if(!resultat.next()){
				int id = this.creerIdVote(request,bdd);
				System.out.println("valeur vote : " + request.getParameter("valeurVote"));
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
		bdd.closeConnexion();
		this.getServletContext().getRequestDispatcher("/WEB-INF/voteEtudiant.jsp").forward(request, response);
	}
	
	
	private int creerIdVote(HttpServletRequest request, Bdd bdd) {
		Random rnd = new Random();
		ResultSet resultat = null;
		boolean trouve = false;
		int id;
		do{
			id= rnd.nextInt(10000);
			try {
				resultat = bdd.faireSelect( "SELECT *  FROM vote where ID_Vote = "+ id +" ;" );
				if(!resultat.next()){
					trouve = true;
				}
			} catch (SQLException e) {
				System.out.println(e);
			}
		}while(!trouve);
		return id;
	}

}
