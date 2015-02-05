package testServlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

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
		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp")
				.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Bdd bdd = new Bdd();
		bdd.connexionBdd();
		if (request.getParameter("action").equals("Rejoindre")) {
			try {
				Integer.parseInt(request.getParameter("accueil_text_idSession"));
				ArrayList<String> valeurs = new ArrayList<>();
				valeurs.add(request.getParameter("accueil_text_idSession"));
				valeurs.add(request.getParameter("accueil_text_mdpSession"));
				ArrayList<String> typeValeurs = new ArrayList<>();
				typeValeurs.add("int");
				typeValeurs.add("String");
				ResultSet resultat = bdd.faireSelectParam("select * from session where ID_Session=? and password=?;",valeurs, typeValeurs);
				try {
					if (resultat.next()) {
						if(this.ajouterEtudiant(request,bdd,request.getParameter("accueil_text_idSession"))){
							HttpSession session = request.getSession();
							session.setAttribute("identifiantSession",request.getParameter("accueil_text_idSession"));
							this.getServletContext().getRequestDispatcher("/WEB-INF/voteEtudiant.jsp").forward(request, response);
						}else{
							this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
						}
					} else {
						this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
			}
		}
		if (request.getParameter("action").equals("Voter")) {
			HttpSession session = request.getSession();
			ArrayList<String> valeurs = new ArrayList<>();
			valeurs.add(session.getAttribute("identifiantSession").toString());
			valeurs.add(session.getAttribute("identifiantEtudiant").toString());
			System.out.println("idEtu : "+session.getAttribute("identifiantEtudiant")+ " et idSess : "+session.getAttribute("identifiantSession"));
			ArrayList<String> typeValeurs = new ArrayList<>();
			typeValeurs.add("int");
			typeValeurs.add("int");
			ResultSet resultat = bdd.faireSelectParam("select * from vote where ID_Session = ? and ID_Etudiant = ? ;", valeurs, typeValeurs);
			try {
				typeValeurs.add("int");
				if(!resultat.next()){
					int id = this.creerIdVote(request,bdd);
					valeurs.add("3");
					valeurs.add(String.valueOf(id));
					typeValeurs.add("int");
					bdd.faireInsert("insert into vote (ID_Session,ID_Etudiant, valeur,ID_Vote) values ( ?, ?, ?, ? ); ", valeurs, typeValeurs);
				}else{
					valeurs.add(0,"4");
					bdd.faireInsert("update vote set valeur = ? where ID_Session = ? and ID_Etudiant = ? ;",valeurs, typeValeurs);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/voteEtudiant.jsp").forward(request, response);
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
	
	private boolean ajouterEtudiant(HttpServletRequest request, Bdd bdd,String parameter) {
		HttpSession session = request.getSession();
		Random rnd = new Random();
		ResultSet resultat = null;
		boolean trouve = false;
		int id;
		do{
			id= rnd.nextInt(10000);
			try {
				resultat = bdd.faireSelect( "SELECT *  FROM etudiant where ID_Etudiant = "+ id +" ;" );
				if(!resultat.next()){
					trouve = true;
				}
			} catch (SQLException e) {
				System.out.println(e);
			}
		}while(!trouve);
		session.setAttribute("identifiantEtudiant",id);
		ArrayList<String> valeurs = new ArrayList<>();
		valeurs.add(String.valueOf(id));
		ArrayList<String> typeValeurs = new ArrayList<>();
		typeValeurs.add("int");
		int valeurRetourInsert = bdd.faireInsert("insert into etudiant (ID_Etudiant) values (?);", valeurs, typeValeurs);
		return valeurRetourInsert != 0;
	}
	
}
