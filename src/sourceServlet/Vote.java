package sourceServlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import baseDeDonnees.Bdd;

public class Vote {
	
	private ArrayList <String> valeurs;
	private ArrayList<String> typeValeurs;
	
	public Vote(ArrayList <String> valeurs, ArrayList<String> typeValeurs){
		this.valeurs = valeurs;
		this.typeValeurs = typeValeurs;
	}
	
	public int ajouterVote(Bdd bdd, String valeurVote){
		String requeteSelect = "select * from vote where ID_Session = ? and ID_Etudiant = ? ;";
		String requeteInsert = "insert into vote (ID_Session, ID_Etudiant, valeur ,ID_Vote) values ( ?, ?, ?, ? ); ";
		String requeteUpdate = "update vote set valeur = ? where ID_Session = ? and ID_Etudiant = ? ;";
		int id = 0;
		
		ResultSet resultat = bdd.faireSelectParam(requeteSelect,valeurs, typeValeurs);
		try {
			typeValeurs.add("int");
			if (resultat.next()) {
				id = SourceServletVoteEtudiant.creerIdVote(bdd);
				valeurs.add(valeurVote);
				valeurs.add(String.valueOf(id));
				typeValeurs.add("int");
				bdd.faireInsert(requeteInsert, valeurs, typeValeurs);
				System.out.println("INSERT du vote");
			} 
			else {
				valeurs.add(0, valeurVote);
				bdd.faireInsert(requeteUpdate, valeurs, typeValeurs);
				System.out.println("UPDATE du vote");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

}
