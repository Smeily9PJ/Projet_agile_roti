package sourceServlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import baseDeDonnees.Bdd;

public class SourceServletVoteEtudiant {
	
	private HttpSession session;
	private ArrayList <String> valeurs;
	private ArrayList<String> typeValeurs;
	private HttpServletRequest requete;
	
	public SourceServletVoteEtudiant(HttpServletRequest request){
		this.session = requete.getSession();
		
		this.valeurs = new ArrayList<String>();
		this.valeurs.add(session.getAttribute("identifiantSession").toString());
		this.valeurs.add(session.getAttribute("identifiantEtudiant").toString());
		
		this.typeValeurs = new ArrayList<String>();
		this.typeValeurs.add("int");
		this.typeValeurs.add("int");
		
		this.requete = request;
	}
	
	public void ajouterVote(Bdd bdd){
		String requeteSelect = "select * from vote where ID_Session = ? and ID_Etudiant = ? ;";
		String requeteInsert = "insert into vote (ID_Session,ID_Etudiant, valeur,ID_Vote) values ( ?, ?, ?, ? ); ";
		String requeteUpdate = "update vote set valeur = ? where ID_Session = ? and ID_Etudiant = ? ;";
		
		ResultSet resultat = bdd.faireSelectParam(requeteSelect,valeurs, typeValeurs);
		try {
			typeValeurs.add("int");
			if (!resultat.next()) {
				int id = SourceServletVoteEtudiant.creerIdVote(bdd);
				valeurs.add(requete.getParameter("valeurVote"));
				valeurs.add(String.valueOf(id));
				typeValeurs.add("int");
				bdd.faireInsert(requeteInsert,valeurs, typeValeurs);
			} 
			else {
				valeurs.add(0, requete.getParameter("valeurVote"));
				bdd.faireInsert(requeteUpdate,valeurs, typeValeurs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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