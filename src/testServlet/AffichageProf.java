package testServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AffichageProf extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		this.getServletContext().getRequestDispatcher( "/WEB-INF/affichageProf.jsp" ).forward( request, response );
	}
	
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		HttpSession session = request.getSession();
		if(request.getParameter("action").equals("Creer")){
			String mdp = request.getParameter("accueil_text_mdpSession");
			session.setAttribute("mdp", mdp);
			session.setAttribute("timing", request.getParameter("numeric"));
			this.getServletContext().getRequestDispatcher( "/WEB-INF/affichageProf.jsp" ).forward( request, response );
		}
		if(request.getParameter("action").equals("Fin de session")){
			this.getServletContext().getRequestDispatcher( "/WEB-INF/accueil.jsp" ).forward( request, response );
		}
	}
}
