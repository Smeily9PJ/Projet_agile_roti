package servlet;

import java.io.IOException;

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
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(SourceServletAffichageProf.calculHumeurMajoritaire(bdd, (this.ID_Session)));
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
