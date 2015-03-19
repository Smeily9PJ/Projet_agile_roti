package sourceServlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import baseDeDonnees.Bdd;

public class SourceServletVoteEmotion {
	public static int creerIdEmotion(Bdd bdd) {
		return SourceServlet.creerId(bdd, "etudiant", "ID_Etudiant");
	}
	
	public static String creationRequete (HttpServletRequest request, Bdd bdd){

		if (request.getParameter("action").equals("Rejoindre")) {
			ArrayList<String> valeurs = new ArrayList<String>();
			valeurs.add(request.getParameter("accueil_text_idSession"));
			valeurs.add(request.getParameter("accueil_text_mdpSession"));
			ArrayList<String> typeValeurs = new ArrayList<String>();
			typeValeurs.add("int");
			typeValeurs.add("String");
			
			ResultSet resultat = bdd.faireSelectParam("select * from session where ID_Session=? and password=?;",valeurs, typeValeurs);
			try {
				if (resultat.next()) {
					if(SourceServletVoteEmotion.ajouterEtudiant(request,bdd,request.getParameter("accueil_text_idSession"))){
						HttpSession session = request.getSession();
						session.setAttribute("identifiantSession", request.getParameter("accueil_text_idSession"));
						return "/WEB-INF/voteEmotion.jsp";
					}else{
						return "/WEB-INF/accueil.jsp";
					}
				} else {
					return "/WEB-INF/accueil.jsp";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "/WEB-INF/accueil.jsp";
	}
	
	public static boolean ajouterEtudiant(HttpServletRequest request, Bdd bdd, String parameter) {
		HttpSession session = request.getSession();
		int id = SourceServletVoteEmotion.creerIdEmotion(bdd);
		session.setAttribute("identifiantEtudiant",id);
		ArrayList<String> valeurs = new ArrayList<>();
		valeurs.add(String.valueOf(id));
		ArrayList<String> typeValeurs = new ArrayList<>();
		typeValeurs.add("int");
		return bdd.faireInsert("insert into etudiant (ID_Etudiant) values (?);", valeurs, typeValeurs) != 0;
	}
}
