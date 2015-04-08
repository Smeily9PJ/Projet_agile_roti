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

import sourceServlet.SourceServletAccueil;
import baseDeDonnees.Bdd;

public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 6129813892573204568L;
	private Bdd bdd;
	
	public ServletAccueil (){
		this.bdd = new Bdd();
		this.bdd.connexionBdd();
	}
	
	protected void finalize(){
		this.bdd.closeConnexion();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.setAttribute("identifiant", SourceServletAccueil.creerIdSession(this.bdd));
		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp")
				.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (request.getParameter("action").equals("Creer")) {
			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/affichageProf.jsp")
					.forward(request, response);
		}
		
		if (request.getParameter("action").equals("Fin de session")) {
			int id = SourceServletAccueil.supprimerSession(this.bdd, session);
			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/accueil.jsp")
					.forward(request, response);
			session.setAttribute("identifiant",id);
		}
		
	}

}
