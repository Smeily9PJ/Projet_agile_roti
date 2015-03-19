package servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sourceServlet.SourceServletVoteEmotion;
import baseDeDonnees.Bdd;

public class ServletVoteEmotion extends HttpServlet {
	private static final long serialVersionUID = -3106663160769817261L;
	private Bdd bdd;
	
	public ServletVoteEmotion (){
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
		String requete = SourceServletVoteEmotion.creationRequete(request, this.bdd);
		this.getServletContext().getRequestDispatcher(requete).forward(request, response);
	}
	


}
