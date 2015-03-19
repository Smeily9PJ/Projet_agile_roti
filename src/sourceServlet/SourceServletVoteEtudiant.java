package sourceServlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import baseDeDonnees.Bdd;

public class SourceServletVoteEtudiant {
	
	public static int creerIdVote(Bdd bdd) {
		return SourceServlet.creerId(bdd, "vote", "ID_Vote");
	}
	
	public static int afficherNumeroHumeur(String action){
		
		int num = 0;
		switch (action) {
			case "colere":  num=1; break;
			case "triste":  num=2; break;
			case "blaze":   num=3; break;
			case "dort":    num=4; break;
			case "rigole":  num=5; break;
			case "content": num=6; break;
			case "voter":   num=7; break;
		}
		System.out.println(num);
		return num;
	}
	
	public static void effectuerVote(HttpServletRequest requete, Bdd bdd){
		
		HttpSession session = requete.getSession();
		ArrayList<String> valeurs = new ArrayList<>();
		valeurs.add(session.getAttribute("identifiantSession").toString());
		valeurs.add(session.getAttribute("identifiantEtudiant").toString());
		ArrayList<String> typeValeurs = new ArrayList<>();
		typeValeurs.add("int");
		typeValeurs.add("int");
		
		ResultSet resultat = bdd.faireSelectParam(
						"select * from vote where ID_Session = ? and ID_Etudiant = ? ;",
						valeurs, typeValeurs);
		try {
			typeValeurs.add("int");
			if (!resultat.next()) {
				int id = SourceServletVoteEtudiant.creerIdVote(bdd);
				valeurs.add(requete.getParameter("valeurVote"));
				valeurs.add(String.valueOf(id));
				typeValeurs.add("int");
				bdd.faireInsert(
						"insert into vote (ID_Session,ID_Etudiant, valeur,ID_Vote) values ( ?, ?, ?, ? ); ",
						valeurs, typeValeurs);
			} else {
				valeurs.add(0, requete.getParameter("valeurVote"));
				bdd.faireInsert(
						"update vote set valeur = ? where ID_Session = ? and ID_Etudiant = ? ;",
						valeurs, typeValeurs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}