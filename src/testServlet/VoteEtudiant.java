package testServlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import queryServlet.Bdd;

public class VoteEtudiant extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Bdd bdd = new Bdd();
		bdd.connexionBdd();
		if(request.getParameter("action").equals("Rejoindre")){
			try{
				Integer.parseInt(request.getParameter("accueil_text_idSession"));
				ArrayList<String> valeurs = new ArrayList<>();
				valeurs.add(request.getParameter("accueil_text_idSession"));
				valeurs.add(request.getParameter("accueil_text_mdpSession"));
				ArrayList<String> typeValeurs = new ArrayList<>();
				typeValeurs.add("int");
				typeValeurs.add("String");
				ResultSet resultat = bdd.faireSelectParam("select * from session where ID_Session=? and password=?;", valeurs, typeValeurs);
				
				try {
					if(resultat.next()){
						this.getServletContext().getRequestDispatcher("/WEB-INF/voteEtudiant.jsp").forward(request, response);
						
					}else{
						this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}catch(Exception e){
				this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
			}
			
		}
		bdd.closeConnexion();		
	}
	
	protected void dispatch(HttpServletRequest request,
            HttpServletResponse response, String page)
            throws javax.servlet.ServletException, java.io.IOException {
        RequestDispatcher dispatcher = getServletContext()
            .getRequestDispatcher(page);
        dispatcher.forward(request, response);
}
}
