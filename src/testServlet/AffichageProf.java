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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext()
				.getRequestDispatcher("/WEB-INF/affichageProf.jsp")
				.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (request.getParameter("action").equals("Creer")) {
			String mdp = request.getParameter("accueil_text_mdpSession");
			session.setAttribute("mdp", mdp);
			session.setAttribute("timing", request.getParameter("numeric"));
			Bdd bdd = new Bdd();
			bdd.connexionBdd();

			/* Exécution d'une requête d'écriture */
			String identifiant = session.getAttribute("identifiant").toString();
			String mdpp = session.getAttribute("mdp").toString();
			String timing =session.getAttribute("timing").toString();
			ArrayList<String> valeurs = new ArrayList<>();
			valeurs.add(identifiant);
			valeurs.add(mdpp);
			valeurs.add(timing);
			ArrayList<String> typeValeurs = new ArrayList<>();
			typeValeurs.add("int");
			typeValeurs.add("String");
			typeValeurs.add("int");
			/* Création de l'objet gérant les requêtes préparées */
			bdd.faireInsert("insert into session (ID_Session, password, interval_Vote) values (?, ?, ?);", valeurs, typeValeurs);
			bdd.closeConnexion();
			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/affichageProf.jsp")
					.forward(request, response);
		}
		if (request.getParameter("action").equals("Fin de session")) {
			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/accueil.jsp")
					.forward(request, response);
		}
	}
}
