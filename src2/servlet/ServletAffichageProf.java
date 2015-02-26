package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sourceServlet.SourceServletAffichageProf;

public class ServletAffichageProf extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/affichageProf.jsp")
				.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SourceServletAffichageProf source = new SourceServletAffichageProf(request);
		if (request.getParameter("action").equals("Creer")) {
			source.creationDonneesInsertProfesseur();
			source.detailSession();
			this.getServletContext().getRequestDispatcher("/WEB-INF/affichageProf.jsp")
					.forward(request, response);
		}
		if (request.getParameter("action").equals("Fin de session")) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp")
					.forward(request, response);
		}
	}
}
