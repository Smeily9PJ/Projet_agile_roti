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
			ArrayList<String> valeurs = new ArrayList<String>();
			valeurs.add(session.getAttribute("identifiant").toString());
			@SuppressWarnings("serial")
			ArrayList<String> typeValeurs = new ArrayList<String>() {{add("int");}};
			ResultSet idsEtudiants = bdd.faireSelectParam("select ID_Etudiant from vote where ID_Session=?", valeurs, typeValeurs);
			bdd.faireDelete("DELETE FROM vote WHERE ID_Session=?", valeurs, typeValeurs);
			try {
				while(idsEtudiants.next()){
					ArrayList<String> valeur = new ArrayList<String>();
					try {
						valeurs.add(String.valueOf(idsEtudiants.getInt(0)));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					@SuppressWarnings("serial")
					ArrayList<String> typeValeur = new ArrayList<String>() {{add("int");}};
					bdd.faireDelete("DELETE FROM etudiant WHERE ID_Etudiant=?", valeur, typeValeur);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bdd.faireDelete("DELETE FROM session WHERE ID_Session=?", valeurs, typeValeurs);
			int id = SourceServletAccueil.creerIdSession(bdd);
			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/accueil.jsp")
					.forward(request, response);
			session.setAttribute("identifiant",id);
		}
		
	}

}
