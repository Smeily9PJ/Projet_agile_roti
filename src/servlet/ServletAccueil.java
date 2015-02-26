package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sourceServlet.SourceServletAccueil;
import baseDeDonnees.Bdd;

public class ServletAccueil extends HttpServlet {
	
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
			ArrayList<String> valeurs = new ArrayList<String>();
			valeurs.add(session.getAttribute("identifiant").toString());
			@SuppressWarnings("serial")
			ArrayList<String> typeValeurs = new ArrayList<String>() {{add("int");}};
			bdd.faireDelete("DELETE FROM session WHERE id_session=?", valeurs, typeValeurs);
			session.invalidate();
			int id = SourceServletAccueil.creerIdSession(bdd);
			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/accueil.jsp")
					.forward(request, response);
			session.setAttribute("identifiant",id);
		}
		
	}

}
