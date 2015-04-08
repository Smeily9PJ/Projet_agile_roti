package test;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sourceServlet.SourceServletAjouterVote;
import sourceServlet.SuppressionSession;
import baseDeDonnees.Bdd;

public class Test_SuppressionSession {

	private Bdd baseDonnee;
	private SuppressionSession monsieur;
	private final int ID_SESSION = 4444;
	private final int ID_ETUDIANT = 4444;
	
	@Before
	public void setUp() throws Exception {
		this.baseDonnee = new Bdd();
		this.baseDonnee.connexionBdd();
		this.monsieur = new SuppressionSession (baseDonnee, ID_SESSION+"");
	}

	@After
	public void tearDown() throws Exception {
		this.baseDonnee.closeConnexion();
		this.baseDonnee = null;
		this.monsieur = null;
	}

	@Test
	public void test_idEtudiantPourSessionEnCours() {
		
		int idVote = 0;	
		insertEnv("test_idEtudiantPourSessionEnCours");
		
		String requete = "select ID_Etudiant from vote where ID_Session=4444";
		ResultSet result = baseDonnee.faireSelect(requete);	
		
		try {
			while (result.next()){
				idVote = result.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("test_idEtudiantPourSessionEnCours ID_VOTE = " + idVote);
		assertNotEquals(0, idVote);
		
		deleteEnv("test_idEtudiantPourSessionEnCours");
	}
	
	@Test
	public void test_deleteVote() {
		insertEnv("test_deleteVote");
		
		String requete = "select ID_Etudiant from vote where ID_Session=4444";
		ResultSet idEtudiants = baseDonnee.faireSelect(requete);	
		monsieur.deleteVote(idEtudiants);
		
		idEtudiants = baseDonnee.faireSelect(requete);	
		int nbOccurences = 0;
		try {
			while (idEtudiants.next()){
				nbOccurences ++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("test_deleteVote : SUPPRESSION SESSION");
		monsieur.deleteSession();
		
		assertEquals(0, nbOccurences);
	}

	@Test
	public void test_deleteEtudiant (){
		insertEtudiant();
		String requete = "select ID_Etudiant from etudiant where ID_Etudiant=4444";
		ResultSet resultatRequete = baseDonnee.faireSelect(requete);
		if (resultatRequete != null){
			monsieur.deleteEtudiant(resultatRequete);
			ResultSet resultatRequeteApresDelete = baseDonnee.faireSelect(requete);
			int nbEtudiant4444 = 0;
			try {
				while(resultatRequeteApresDelete.next()){
					nbEtudiant4444 ++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			assertEquals(0, nbEtudiant4444);
			
		}
		else
			fail("Il n'y a pas d'etudiant 4444 dans la table vote");
	}
	
	@Test
	public void test_deleteSession() {
		String session4444 = "select ID_Session from session where ID_Session = 4444";
		
		lancerInsertSession_ID_SESSION();
		monsieur.deleteSession();
		ResultSet session4444DansBdd = baseDonnee.faireSelect(session4444);
		
		int nbSession4444 = 0;
		try {
			while (session4444DansBdd.next()){
				nbSession4444 ++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(0, nbSession4444);
	}
	
	@Test
	public void test_nouvelIdSession() {	
		assertNotEquals(ID_SESSION, this.monsieur.nouvelIdSession());
	}
	
	private void lancerInsertSession_ID_SESSION(){
		String requeteInsert = "INSERT INTO session (ID_Session, password, interval_vote) values (?, ?, ?);";
		@SuppressWarnings("serial")
		ArrayList<String> valeursInsert = new ArrayList<String>(){{add(ID_SESSION + ""); add("PASS_TEST"); add("1");}};
		@SuppressWarnings("serial")
		ArrayList<String> typevaleurDelete = new ArrayList<String>(){{add("int");add("String");add("int");}};
		
		baseDonnee.faireInsert(requeteInsert, valeursInsert, typevaleurDelete);
	}
	
	private void insertEtudiant (){
		String requeteInsert = "INSERT INTO etudiant (ID_Etudiant, Emotion) values (?, ?);";
		@SuppressWarnings("serial")
		ArrayList<String> valeursInsert = new ArrayList<String>(){{add(ID_ETUDIANT + ""); add("dort"); }};
		@SuppressWarnings("serial")
		ArrayList<String> typevaleurDelete = new ArrayList<String>(){{add("int"); add("String");}};
		
		baseDonnee.faireInsert(requeteInsert, valeursInsert, typevaleurDelete);
		
	}
	
	private void insertVote (){
		final int idVote = SourceServletAjouterVote.creerIdSession(this.baseDonnee);
		String requeteInsert = "INSERT INTO vote (ID_Vote, valeur, ID_Session, ID_Etudiant) values (?, ?, ?, ?);";
		@SuppressWarnings("serial")
		ArrayList<String> valeursInsert = new ArrayList<String>(){{add(idVote + ""); add("4444"); add(ID_SESSION+""); add(ID_ETUDIANT+"");}};
		@SuppressWarnings("serial")
		ArrayList<String> typevaleurDelete = new ArrayList<String>(){{add("int");add("int");add("int");add("int");}};
		
		baseDonnee.faireInsert(requeteInsert, valeursInsert, typevaleurDelete);
		
	}
	
	private void insertEnv (String nomAppelant){
		System.out.println(nomAppelant + " : AJOUT SESSION");
		lancerInsertSession_ID_SESSION();
		System.out.println(nomAppelant + " : AJOUT ETUDIANT");
		insertEtudiant();
		System.out.println(nomAppelant + " : AJOUT VOTE");
		insertVote();
	}
	
	private void deleteEnv (String nomAppelant){
		
		String vote = "DELETE FROM vote WHERE ID_Etudiant = ?";
		String etudiant = "DELETE FROM etudiant WHERE ID_Etudiant = ?";
		String session = "DELETE FROM session WHERE ID_Session = ?";
		
		ArrayList<String> valeurs = new  ArrayList<String>() {
			private static final long serialVersionUID = 1L;
		{add("4444");}};
		
		ArrayList<String> typeValeurs = new  ArrayList<String>() {
			private static final long serialVersionUID = 1342417091354803177L;
		{add("int");}};
		
		System.out.println(nomAppelant + " : SUPPRESSION VOTE");
		baseDonnee.faireDelete(vote, valeurs, typeValeurs);
		System.out.println(nomAppelant + " : SUPPRESSION ETUDIANT");
		baseDonnee.faireDelete(etudiant, valeurs, typeValeurs);
		System.out.println(nomAppelant + " : SUPPRESSION SESSION");
		baseDonnee.faireDelete(session, valeurs, typeValeurs);
	}
	
	
}
