package servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
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

import baseDeDonnees.Bdd;

public class ServletAffichageProf extends HttpServlet {
	private String ID_Session;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String resultat = "invalide";
		String humeurMajoritaire = "";
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		float moyenne = 0;
		Bdd bdd = new Bdd();
		bdd.connexionBdd();
		ArrayList<String> valeurs = new ArrayList<>();
		valeurs.add(ID_Session);
		ArrayList<String> typeValeurs = new ArrayList<>();
		typeValeurs.add("String");
		ResultSet resultatSelect = bdd.faireSelectParam(
				"select * from vote where ID_Session = ? ; ", valeurs,
				typeValeurs);
		int nbPersonnes = 0;
		try {
			ArrayList<Integer> listeVote = new ArrayList<Integer>();
			while (resultatSelect.next()) {
				humeurMajoritaire = trouverHumeurMajoritaire(resultatSelect.getInt("Id_Etudiant"), bdd);
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

	private String trouverHumeurMajoritaire(int idEtudiant, Bdd bdd) {
		String humeur = "colere";
		ArrayList<String> valeurs = new ArrayList<String>();
		valeurs.add(String.valueOf(idEtudiant));
		ArrayList<String> typeValeurs = new ArrayList<String>();
		valeurs.add("int");
		ResultSet resultatHumeur = bdd.faireSelectParam("select emotion where ID_Etudiant=?", valeurs, typeValeurs);
		HashMap<String,Integer> listeHumeurs = new HashMap<>();
		listeHumeurs.put("colere", 0);
		listeHumeurs.put("blaze", 0);
		listeHumeurs.put("dort", 0);
		listeHumeurs.put("content", 0);
		listeHumeurs.put("triste", 0);
		listeHumeurs.put("rigole", 0);
		try {
			while(resultatHumeur.next()){
				listeHumeurs.put(resultatHumeur.getString("emotion"), listeHumeurs.get(resultatHumeur.getString("emotion"))+1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Set cles = listeHumeurs.keySet();
		Iterator it = cles.iterator();
		while (it.hasNext()){
		   String humeurMap = (String) it.next();
		   if(listeHumeurs.get(humeur) <= listeHumeurs.get(humeurMap)){
			   humeur = humeurMap;
		   }
		}
		return humeur;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			bdd.faireInsert(
					"insert into session (ID_Session, password, interval_Vote) values (?, ?, ?);",
					valeurs, typeValeurs);
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
