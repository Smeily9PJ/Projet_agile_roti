package sourceServlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import baseDeDonnees.Bdd;

public class HumeurMajoritaire {

	private Bdd bdd;
	private String humeurMajoritaire;
	private String idSession;
	int nbPersonnes = 0;
	float moyenne = 0f;
	
	public HumeurMajoritaire (Bdd bdd, String idSession){
		this.bdd = bdd;
		this.humeurMajoritaire = "";
		this.idSession = idSession;
		this.nbPersonnes = 0;
		this.moyenne = 0f;
		calculHumeurMajoritaire ();
	}
	
	public String getHumeurMajoritaire (){
		return this.humeurMajoritaire;
	}
	
	public String toString (){
		return moyenne + "&" + nbPersonnes + "&" + humeurMajoritaire;
	}

	private void calculHumeurMajoritaire () {		
		this.humeurMajoritaire = "";
		
		ArrayList<Integer> listeVote = new ArrayList<Integer>();
		ResultSet voteDeLaSession = requete_VoteDeLaSession();
		
		try {
			while (voteDeLaSession.next()) {
				humeurMajoritaire = trouverHumeur(voteDeLaSession.getInt("Id_Etudiant"), bdd);
				listeVote.add(voteDeLaSession.getInt("valeur"));
			}
			for (int val : listeVote) {
				nbPersonnes++;
				moyenne += val;
			}
			moyenne /= nbPersonnes;
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	/** 
	 * @return l'humeur de l'étudiant idEtudiant
	 */
	private String trouverHumeur(int idEtudiant, Bdd bdd) {
		Humeur humeur = new Humeur(idEtudiant, bdd);
		return humeur.trouverHumeur();
	}
	
	private ResultSet requete_VoteDeLaSession (){
		String requeteSelectVote = "select * from vote where ID_Session = ? ; ";

		ArrayList<String> valeurs = new ArrayList<>();
		valeurs.add(idSession);
		
		ArrayList<String> typeValeurs = new ArrayList<>();
		typeValeurs.add("String");
		
		ResultSet voteDeLaSession = bdd.faireSelectParam(requeteSelectVote, valeurs, typeValeurs);
		return voteDeLaSession;
	}
		
		
}
