package testServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Accueil extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		this.getServletContext().getRequestDispatcher( "/WEB-INF/accueil.jsp" ).forward( request, response );
	}
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		if(request.getParameter("action").equals("Creer")){
			this.getServletContext().getRequestDispatcher( "/WEB-INF/creer.jsp" ).forward( request, response );
		}
		if(request.getParameter("action").equals("Rejoindre")){
			this.getServletContext().getRequestDispatcher( "/WEB-INF/voteEtudiant.jsp" ).forward( request, response );
		}
	}
	
}
