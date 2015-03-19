package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
			SourceServletVoteEtudiant.effectuerVote(request, this.bdd);
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/voteEtudiant.jsp").forward(request, response);
		
	}

}
