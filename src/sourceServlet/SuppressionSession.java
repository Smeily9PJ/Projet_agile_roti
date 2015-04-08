package sourceServlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import baseDeDonnees.Bdd;

public class SuppressionSession {

	private ArrayList<String> valeurs;
	private ArrayList<String> typeValeurs;
	private Bdd bdd;
	
	@SuppressWarnings("serial")
	public SuppressionSession (Bdd baseDonnee, String identifiant){
		this.valeurs = new ArrayList<String>();
		valeurs.add(identifiant);
		this.typeValeurs = new ArrayList<String>() {{add("int");}};
		this.bdd = baseDonnee;
	}
	
	//Retourne l'id de la nouvelle session
	public int supprimerSession (){
		deleteVote (idEtudiantPourSessionEnCours());
		deleteSession();
		return nouvelIdSession();
	}
	
	public ResultSet idEtudiantPourSessionEnCours (){
		String requete = "select ID_Etudiant from vote where ID_Session=?";
		return bdd.faireSelectParam(requete, valeurs, typeValeurs);	
	}
	
	public void deleteVote (ResultSet idEtudiants){
		String deleteVoteSession = "DELETE FROM vote WHERE ID_Session=?";
		
		bdd.faireDelete(deleteVoteSession, valeurs, typeValeurs);
		deleteEtudiant(idEtudiants);
	}
	
	@SuppressWarnings("serial")
	public void deleteEtudiant (ResultSet idEtudiant){
		String deleteEtudiant = "DELETE FROM etudiant WHERE ID_Etudiant=?";
		
		ArrayList<String> valeur = new ArrayList<String>();
		try {
			while (idEtudiant.next()){
				String idADelete = idEtudiant.getInt(1) + "";
				valeur.add(idADelete);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ArrayList<String> typeValeur = new ArrayList<String>() {{add("int");}};
		bdd.faireDelete(deleteEtudiant, valeur, typeValeur);
	}
	
	public void deleteSession (){
		String deleteSession = "DELETE FROM session WHERE ID_Session=?";
		bdd.faireDelete(deleteSession, valeurs, typeValeurs);
	}
	
	public int nouvelIdSession (){
		return SourceServletAccueil.creerIdSession(bdd);
	}
	
}
