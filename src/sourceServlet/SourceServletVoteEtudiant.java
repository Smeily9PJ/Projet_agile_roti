package sourceServlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import baseDeDonnees.Bdd;

public class SourceServletVoteEtudiant {
	
	private ArrayList <String> valeurs;
	private ArrayList<String> typeValeurs;
	
	public SourceServletVoteEtudiant(HttpServletRequest request){
		HttpSession session = request.getSession();
		
		this.valeurs = new ArrayList<String>();
		this.valeurs.add(session.getAttribute("identifiantSession").toString());
		this.valeurs.add(session.getAttribute("identifiantEtudiant").toString());
		
		this.typeValeurs = new ArrayList<String>();
		this.typeValeurs.add("int");
		this.typeValeurs.add("int");
	}
	
	public void ajouterVote(Bdd bdd, String valeurVote){
		Vote v = new Vote(valeurs, typeValeurs);
		v.ajouterVote(bdd, valeurVote);
	}
		
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
			
}