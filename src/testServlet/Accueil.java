package testServlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import queryServlet.Bdd;

public class Accueil extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		this.creerIdSession(request);
		this.getServletContext().getRequestDispatcher( "/WEB-INF/accueil.jsp" ).forward( request, response );
}
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		HttpSession session = request.getSession();
		if(request.getParameter("action").equals("Creer")){
			this.getServletContext().getRequestDispatcher( "/WEB-INF/affichageProf.jsp" ).forward( request, response );
		}
		if(request.getParameter("action").equals("Rejoindre")){
			this.getServletContext().getRequestDispatcher( "/WEB-INF/voteEtudiant.jsp" ).forward( request, response );
		}
		if(request.getParameter("action").equals("Fin de session")){
			session.invalidate();
			this.creerIdSession(request);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/accueil.jsp" ).forward( request, response );
		}
	}
	
	private void creerIdSession(HttpServletRequest request){
		HttpSession session = request.getSession();
		Random rnd = new Random();
		ResultSet resultat = null;
		Bdd bdd = new Bdd();
		bdd.connexionBdd();
		boolean trouve = false;
		int id;
		do{
			id= rnd.nextInt(10000);
			try {
				resultat = bdd.faireSelect( "SELECT *  FROM session where ID_Session = "+ id +" ;" );
				if(!resultat.next()){
					trouve = true;
				}
			} catch (SQLException e) {
				System.out.println(e);
			}
		}while(!trouve);
		bdd.closeConnexion();
		session.setAttribute("identifiant",id);
	}
	
}
