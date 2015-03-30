package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sourceServlet.SourceServletAffichageProf;
import baseDeDonnees.Bdd;

public class ServletAffichageProf extends HttpServlet {
	private static final long serialVersionUID = 6720344203824153474L;

	private String ID_Session;

	private Bdd bdd;

	public ServletAffichageProf() {
		this.bdd = new Bdd();
		this.bdd.connexionBdd();
	}

	protected void finalize() {
		this.bdd.closeConnexion();
	}

	//public void calculHumeur
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String humeurMajoritaire = "";
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		float moyenne = 0;
		ArrayList<String> valeurs = new ArrayList<>();
		valeurs.add(this.ID_Session);
		ArrayList<String> typeValeurs = new ArrayList<>();
		typeValeurs.add("String");
		ResultSet resultatSelect = bdd.faireSelectParam(
				"select * from vote where ID_Session = ? ; ", valeurs,
				typeValeurs);
		int nbPersonnes = 0;
		try {
			ArrayList<Integer> listeVote = new ArrayList<Integer>();
			while (resultatSelect.next()) {
				humeurMajoritaire = SourceServletAffichageProf.trouverHumeurMajoritaire(resultatSelect.getInt("Id_Etudiant"), bdd);
				listeVote.add(resultatSelect.getInt("valeur"));
			}
			for (int val : listeVote) {
				nbPersonnes++;
				moyenne += val;
			}
			moyenne = moyenne / nbPersonnes;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.getWriter().write(
				moyenne + "&" + nbPersonnes + "&" + humeurMajoritaire);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		this.ID_Session = session.getAttribute("identifiant").toString();
		SourceServletAffichageProf source = new SourceServletAffichageProf(
				request, this);
		if (request.getParameter("action").equals("Creer")) {
			source.detailSession();
			source.creationDonneesInsertProfesseur();
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
