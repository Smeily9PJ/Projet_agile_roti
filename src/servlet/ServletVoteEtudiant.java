package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;

import sourceServlet.SourceServletVoteEtudiant;
import baseDeDonnees.Bdd;

public class ServletVoteEtudiant extends HttpServlet {

	private static final long serialVersionUID = 2718462721938890332L;
	private Bdd bdd;
	
	public ServletVoteEtudiant (){
		this.bdd = new Bdd();
		this.bdd.connexionBdd();
	}
	
	protected void finalize(){
		this.bdd.closeConnexion();
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp")
		.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int numHumeur = SourceServletVoteEtudiant.afficherNumeroHumeur(request.getParameter("action"));

		if (numHumeur == 7){
			SourceServletVoteEtudiant vote = new SourceServletVoteEtudiant(request);
			vote.ajouterVote(bdd, request.getParameter("valeurVote"));
		}else {
			HttpSession session = request.getSession();
			ArrayList<String> valeurs = new ArrayList<String>();
			valeurs.add(request.getParameter("action"));
			valeurs.add(session.getAttribute("identifiantEtudiant").toString());
			ArrayList<String> typeValeurs = new ArrayList<String>();
			typeValeurs.add("String");
			typeValeurs.add("int");
			bdd.faireInsert("update etudiant set emotion = ? where ID_Etudiant = ?", valeurs, typeValeurs);
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/voteEtudiant.jsp").forward(request, response);
		
	}

}
