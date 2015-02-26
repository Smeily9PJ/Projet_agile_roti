package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import baseDeDonnees.Bdd;

public class ServletVoteEtudiant extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp")
				.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Bdd bdd = new Bdd();
		bdd.connexionBdd();
		switch(request.getParameter("action")) {
		case "colere" : 
			System.out.println("1");
			this.getServletContext().getRequestDispatcher("/WEB-INF/voteEtudiant.jsp").forward(request, response);
			break;
		case "triste" : 
			System.out.println("2");
			this.getServletContext().getRequestDispatcher("/WEB-INF/voteEtudiant.jsp").forward(request, response);
			break;
		case "blaze" : 
			System.out.println("3");
			this.getServletContext().getRequestDispatcher("/WEB-INF/voteEtudiant.jsp").forward(request, response);
			break;
		case "dort" : 
			System.out.println("4");
			this.getServletContext().getRequestDispatcher("/WEB-INF/voteEtudiant.jsp").forward(request, response);
			break;
		case "rigole" :
			System.out.println("5");
			this.getServletContext().getRequestDispatcher("/WEB-INF/voteEtudiant.jsp").forward(request, response);
			break;
		case "content" : 
			System.out.println("6");
			this.getServletContext().getRequestDispatcher("/WEB-INF/voteEtudiant.jsp").forward(request, response);
			break;
		default:
			if (request.getParameter("action").equals("Voter")) {
				HttpSession session = request.getSession();
				ArrayList<String> valeurs = new ArrayList<>();
				valeurs.add(session.getAttribute("identifiantSession").toString());
				valeurs.add(session.getAttribute("identifiantEtudiant").toString());
				ArrayList<String> typeValeurs = new ArrayList<>();
				typeValeurs.add("int");
				typeValeurs.add("int");
				ResultSet resultat = bdd.faireSelectParam("select * from vote where ID_Session = ? and ID_Etudiant = ? ;", valeurs, typeValeurs);
				try {
					typeValeurs.add("int");
					if(!resultat.next()){
						int id = this.creerIdVote(request,bdd);
						valeurs.add(request.getParameter("valeurVote"));
						valeurs.add(String.valueOf(id));
						typeValeurs.add("int");
						bdd.faireInsert("insert into vote (ID_Session,ID_Etudiant, valeur,ID_Vote) values ( ?, ?, ?, ? ); ", valeurs, typeValeurs);
					}else{
						valeurs.add(0,request.getParameter("valeurVote"));
						bdd.faireInsert("update vote set valeur = ? where ID_Session = ? and ID_Etudiant = ? ;",valeurs, typeValeurs);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				this.getServletContext().getRequestDispatcher("/WEB-INF/voteEtudiant.jsp").forward(request, response);
			}
		}
		bdd.closeConnexion();
	}
	
	private int creerIdVote(HttpServletRequest request, Bdd bdd) {
		Random rnd = new Random();
		ResultSet resultat = null;
		boolean trouve = false;
		int id;
		do{
			id= rnd.nextInt(10000);
			try {
				resultat = bdd.faireSelect( "SELECT *  FROM vote where ID_Vote = "+ id +" ;" );
				if(!resultat.next()){
					trouve = true;
				}
			} catch (SQLException e) {
				System.out.println(e);
			}
		}while(!trouve);
		return id;
	}
}
