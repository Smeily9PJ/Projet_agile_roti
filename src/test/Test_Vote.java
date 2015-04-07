package test;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import baseDeDonnees.Bdd;
import sourceServlet.SourceServletAjouterVote;
import sourceServlet.Vote;

/***
 * Test l'insertion dans la base de donnée d'un nouveau vote
 * 
 * Nécessite : 
 * identifiantSession = 1245
 * 	INSERT INTO Session values (1245, "pass", 1);
 * identifiantEtudiant = 1566
 * 	INSERT INTO Etudiant values (1566);
 */
public class Test_Vote {

	
	private ArrayList <String> valeurs;
	private ArrayList<String> typeValeurs;
	private Bdd bdd;
	private final int ID_ETUDIANT = 1566;
	private final int ID_SESSION = 1245;
	private final int valeurDuVote = 42;
	
	@Before
	public void setUp() throws Exception {
		
		this.bdd = new Bdd();
		this.bdd.connexionBdd();
		
		this.valeurs = new ArrayList<String>();
		this.valeurs.add(ID_SESSION + ""); //id_session
		this.valeurs.add(ID_ETUDIANT + ""); //id_etudiant
		this.typeValeurs = new ArrayList<String>();
		this.typeValeurs.add("int");
		this.typeValeurs.add("int");	
		
	}

	@After
	public void tearDown() throws Exception {
		this.bdd.closeConnexion();
		this.bdd = null;
		//this.v = null;
	}

	@Test
	public void test_ajouterVote_INSERT() {
		
		lancerDelete();
		
		Vote v = new Vote(valeurs, typeValeurs);
		int idVote = v.ajouterVote(bdd, valeurDuVote+"");
		
		String requete = "select * from vote where ID_Vote = " + idVote + ";";
		ResultSet resultat = bdd.faireSelect(requete);
		int idVoteLocal=0;
		int valeur=0;
		int idSession=0;
		int idEtudiant=0;

		try {
			if (resultat.next()){
				idVoteLocal = resultat.getInt("ID_Vote");
				valeur = resultat.getInt("valeur");
				idSession = resultat.getInt("ID_Session"); 
				idEtudiant = resultat.getInt("ID_Etudiant"); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("idVote = " + idVoteLocal + " valeur = " + valeur + 
				" idsession = " + idSession + " idetudiant = " + idEtudiant );
		
		assertEquals(idVote, idVoteLocal);
		assertEquals(this.valeurDuVote, valeur);
		assertEquals(ID_SESSION, idSession);
		assertEquals(ID_ETUDIANT, idEtudiant);
	}	
	
	@Test
	public void test_ajouterVote_UPDATE() {
		int idVote = SourceServletAjouterVote.creerIdSession(this.bdd);
		lancerInsert(idVote);
		
		Vote v = new Vote(valeurs, typeValeurs);
		v.ajouterVote(bdd, valeurDuVote+"");

		
		String requete = "select * from vote where ID_Vote = " + idVote + ";";
		ResultSet resultat = bdd.faireSelect(requete);
		int idVoteLocal=0;
		int valeur=0;
		int idSession=0;
		int idEtudiant=0;

		try {
			if (resultat.next()){
				idVoteLocal = resultat.getInt("ID_Vote");
				valeur = resultat.getInt("valeur");
				idSession = resultat.getInt("ID_Session"); 
				idEtudiant = resultat.getInt("ID_Etudiant"); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("idVote = " + idVoteLocal + " valeur = " + valeur + 
				" idsession = " + idSession + " idetudiant = " + idEtudiant );
		
		assertEquals(idVote, idVoteLocal);
		assertEquals(this.valeurDuVote, valeur);
		assertEquals(ID_SESSION, idSession);
		assertEquals(ID_ETUDIANT, idEtudiant);
	}		
		
		
	private void lancerDelete(){
		String requeteDelete = "DELETE FROM vote WHERE ID_Session = ? and ID_Etudiant = ?;";

		@SuppressWarnings("serial")
		ArrayList<String> valeurDelete = new ArrayList<String>(){{add("1245");add("1566");}};
		@SuppressWarnings("serial")
		ArrayList<String> typevaleurDelete = new ArrayList<String>(){{add("int");add("int");}};
		bdd.faireDelete(requeteDelete, valeurDelete, typevaleurDelete);
	}
	
	private void lancerInsert (final int idVote){
		String requeteInsert = "INSERT INTO vote (ID_Vote, valeur, ID_Session, ID_Etudiant) values (?, ?, ?, ?);";
		@SuppressWarnings("serial")
		ArrayList<String> valeursInsert = new ArrayList<String>(){{add(idVote + ""); add("42"); add("1245"); add("1566");}};
		@SuppressWarnings("serial")
		ArrayList<String> typevaleurDelete = new ArrayList<String>(){{add("int");add("int");add("int");add("int");}};
		
		bdd.faireInsert(requeteInsert, valeursInsert, typevaleurDelete);
	}
}
