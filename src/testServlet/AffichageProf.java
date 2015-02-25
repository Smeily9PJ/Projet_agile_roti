package testServlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import queryServlet.Bdd;

public class AffichageProf extends HttpServlet {
	private String ID_Session ;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String resultat = "invalide";
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		float moyenne =0;
			Bdd bdd = new Bdd();
			bdd.connexionBdd();
			ArrayList<String> valeurs = new ArrayList<>();
			valeurs.add(ID_Session);
			ArrayList<String> typeValeurs = new ArrayList<>();
			typeValeurs.add("String");
			ResultSet resultatSelect = bdd.faireSelectParam("select * from vote where ID_Session = ? ; ",valeurs, typeValeurs);
			int nbPersonnes = 0;
			try {
				ArrayList<Integer> listeVote = new ArrayList<Integer>();
				while (resultatSelect.next()){
					listeVote.add(resultatSelect.getInt("valeur"));
				}
				for(int val : listeVote){
					nbPersonnes++;
					moyenne += val;
				}
				moyenne = moyenne/nbPersonnes;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		response.getWriter().write(moyenne+"&"+nbPersonnes);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ID_Session = session.getAttribute("identifiant").toString();
		if (request.getParameter("action").equals("Creer")) {
			String mdp = request.getParameter("accueil_text_mdpSession");
			session.setAttribute("mdp", mdp);
			session.setAttribute("timing", request.getParameter("numeric"));
			Bdd bdd = new Bdd();
			bdd.connexionBdd();
			ArrayList<String> valeurs = new ArrayList<>();
			valeurs.add(session.getAttribute("identifiant").toString());
			valeurs.add(session.getAttribute("mdp").toString());
			valeurs.add(session.getAttribute("timing").toString());
			ArrayList<String> typeValeurs = new ArrayList<>();
			typeValeurs.add("int");
			typeValeurs.add("String");
			typeValeurs.add("int");
			bdd.faireInsert("insert into session (ID_Session, password, interval_Vote) values (?, ?, ?);", valeurs, typeValeurs);			
			bdd.closeConnexion();
			this.getServletContext().getRequestDispatcher("/WEB-INF/affichageProf.jsp").forward(request, response);
		}
		if (request.getParameter("action").equals("Fin de session")) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
		}
	}
}
