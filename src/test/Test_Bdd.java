package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import baseDeDonnees.Bdd;
//http://www.ntu.edu.sg/home/ehchua/programming/howto/Tomcat_HowTo.html
/**
	Préconditions aux tests : avoir une ligne avec un id de 30 dans la table Session
							  ne pas avoir d'id de 10 dans la table Session
*/
public class Test_Bdd {

	@SuppressWarnings("serial")
	public static final ArrayList <String> LIST_TYPE_VALEURS_TABLE_SESSION = new ArrayList <String> (){{
	    add("int");
	    add("String");
	    add("int");
	}};
	
	private Bdd baseDeDonee;
	private static String url = "jdbc:mysql://localhost:8888/bddroti";
	private static String utilisateur = "root";
	private static String motDePasse = "root";
	
	
	@Before
	public void setUp() throws Exception {
		baseDeDonee = new Bdd();
		baseDeDonee.connexionBdd();
	}

	@After
	public void tearDown() throws Exception {
		baseDeDonee.closeConnexion();
		if (baseDeDonee.getStatement() == null && baseDeDonee.getConnexion() == null){
			System.out.println("Connexion a la BDD fermée avec succès. (Test closeConnexion)");
		}else {
			System.out.println("ATTENTION : ECHEC de la fermeture de la BDD (Test closeConnexion)");
		}
		baseDeDonee = null;
	}

	@Test
	public void test_connexionBdd() {
		assertNotEquals(null, baseDeDonee.getConnexion());
		assertNotEquals(null, baseDeDonee.getStatement());
	}
	
	@Test
	public void test_getConnexion() {
		Test_Bdd.simulationConnexion();
		assertNotEquals (null, baseDeDonee.getConnexion());
	}
	
	@Test
	public void test_getStatement() {
		Test_Bdd.simulationStatement();
		assertNotEquals(null, baseDeDonee.getStatement());
	}
	
	@Test
	public void test_faireSelect() {
		String requete = "SELECT * FROM Session WHERE Id_Session = 30";
		ResultSet resultat = baseDeDonee.faireSelect(requete);
		String resultatString = Test_Bdd.retourTableSession(resultat);
		assertEquals("30 abc 200|", resultatString);
	}

	@Test
	public void test_faireSelectParam() {
		String requete = "SELECT * FROM Session WHERE Id_Session = ?";
		@SuppressWarnings("serial")
		ArrayList<String> valeurs = new ArrayList<String>(){{add("30");}};
		@SuppressWarnings("serial")
		ArrayList<String> typeValeur = new ArrayList<String>(){{add("int");}};
		
		ResultSet resultat = baseDeDonee.faireSelectParam(requete, valeurs, typeValeur);
		String resultatString = Test_Bdd.retourTableSession(resultat);
		assertEquals("30 abc 200|", resultatString);
	}
	
	@Test
	public void test_faireDelete() {
		String requeteSelect = "SELECT * FROM Session where Id_Session = 10";
		String messageAttenduApresInsert = "10 pass 12|";	
		String messageAttenduApresDelete = "";	
		
		this.lancerInsert();
		ResultSet resultat = baseDeDonee.faireSelect(requeteSelect);
		String retourString = Test_Bdd.retourTableSession(resultat);
		assertEquals(messageAttenduApresInsert, retourString);
		
		this.lancerDelete();
		retourString = Test_Bdd.retourTableSession(resultat);
		assertEquals(messageAttenduApresDelete, retourString);
	}
	
	@Test
	public void test_faireInsert() {
		String requeteSelect = "SELECT * FROM Session where Id_Session = 10";
		String messageAttenduApresInsert = "10 pass 12|";	

		this.lancerInsert();
		ResultSet resultat = baseDeDonee.faireSelect(requeteSelect);
		this.lancerDelete();
		
		String retourString = Test_Bdd.retourTableSession(resultat);
		assertEquals(messageAttenduApresInsert, retourString);
	}
	
	
	/////////////////////
	//METHODES DE TESTS//
	/////////////////////
	
	private static Connection simulationConnexion(){
		Connection connexion = null;
		 try {
			    Class.forName( "com.mysql.jdbc.Driver" );
			} catch ( ClassNotFoundException e ) {
			   System.out.println(e);
			}
		   try {
		       connexion = DriverManager.getConnection( Test_Bdd.url, 
		    		   Test_Bdd.utilisateur, Test_Bdd.motDePasse );
		   } catch ( SQLException e ) {
			   System.out.println(e);
		   }
		   return connexion;
	}
	
	private static Statement simulationStatement(){
		Statement statement = null;
		   try {
			    Class.forName( "com.mysql.jdbc.Driver" );
			} catch ( ClassNotFoundException e ) {
			   System.out.println(e);
			}
		   try {
			   Connection connexion = DriverManager.getConnection( Test_Bdd.url, 
					   Test_Bdd.utilisateur, Test_Bdd.motDePasse );
		       statement = connexion.createStatement();
		   } catch ( SQLException e ) {
			   System.out.println(e);
		   }
		   return statement;
	   }
	
	private static String retourTableSession (final ResultSet resultat){
		String retourResultat = "";
		try {
			while (resultat.next()){
				try {
					int idSession = resultat.getInt("ID_SESSION");
					String password = resultat.getString("PASSWORD");
					int intervalVote = resultat.getInt("INTERVAL_VOTE"); 
					retourResultat += idSession + " " + password + " " + intervalVote + "|";
					
				} catch (SQLException e) {
					System.out.println("ERROR! Dans retourTableSession fichier test_queryServlet_Bdd.java");
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			System.out.println("ERROR! Dans retourTableSession fichier test_queryServlet_Bdd.java");
			e.printStackTrace();
		}
		return retourResultat;
	}
	
	private void lancerInsert (){
		String requeteInsert = "INSERT INTO session (ID_Session, password, interval_Vote) values (?, ?, ?);";
		@SuppressWarnings("serial")
		ArrayList<String> valeursInsert = new ArrayList<String>(){{add("10"); add("pass"); add("12");}};
		baseDeDonee.faireInsert(requeteInsert, valeursInsert, LIST_TYPE_VALEURS_TABLE_SESSION);
	}
	
	private void lancerDelete(){
		String requeteDelete = "DELETE FROM Session WHERE Id_Session = ?";
		@SuppressWarnings("serial")
		ArrayList<String> valeurDelete = new ArrayList<String>(){{add("10");}};
		@SuppressWarnings("serial")
		ArrayList<String> typevaleurDelete = new ArrayList<String>(){{add("int");}};
		baseDeDonee.faireDelete(requeteDelete, valeurDelete, typevaleurDelete);
	}
}

