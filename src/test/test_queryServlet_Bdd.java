package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import queryServlet.Bdd;

public class test_queryServlet_Bdd {

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
		baseDeDonee = null;
	}

	@Test
	public void test_connexionBdd() {
		assertNotEquals(null, baseDeDonee.getConnexion());
		assertNotEquals(null, baseDeDonee.getStatement());
	}
	
	@Test
	public void test_getConnexion() {
		Connection connexion =  test_queryServlet_Bdd.simulationConnexion();
		assertEquals (connexion, baseDeDonee.getConnexion());
	}
	
	@Test
	public void test_getStatement() {
		Statement statement = test_queryServlet_Bdd.simulationStatement();
		assertEquals(statement, baseDeDonee.getStatement());
	}
	
	@Test
	public void test_faireSelect() {
		String requete = "SELECT id_etudiant FROM Etudiant WHERE id_etudiant=0";
		ResultSet resultat = baseDeDonee.faireSelect(requete);
		int test = testInt(resultat);
		assertEquals(0, test);
	}

	
	@Test
	public void test_faireSelectParam() {
		//Todo
	}
	
	@Test
	public void test_faireDelete() {
		/*
		String requeteInsert = "INSERTE INTO Etudiant VALUES (10)";
		String requeteDelete = "DELETE FROM Etudiant WHERE Id_Etudiant = 10";
		String requete = "SELECT * FROM Etudiant where Id_Etudiant = 10";
		ArrayList <String> value = new ArrayList<String>();
		ArrayList <String> type = new ArrayList<String>();
		value.add("10");
		type.add("int");
		
		baseDeDonee.faireInsert(requeteInsert, value, type);
		ResultSet resultat = baseDeDonee.faireSelect(requete);
		int test=-1;
		try {
			while(resultat.next()){
				test = resultat.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(10, test);
		
		
		baseDeDonee.faireDelete(requeteDelete, value, type);
		//assert existe plus
		*/
	}
	
	@Test
	public void test_faireInsert() {
		String requeteInsert = "INSERTE INTO Etudiant VALUES (10)";
		
	}
	
	@Test
	public void test_closeConnexion() {
		
	}

	private static Connection simulationConnexion(){
		Connection connexion = null;
		 try {
			    Class.forName( "com.mysql.jdbc.Driver" );
			} catch ( ClassNotFoundException e ) {
			   System.out.println(e);
			}
		   try {
		       connexion = DriverManager.getConnection( test_queryServlet_Bdd.url, test_queryServlet_Bdd.utilisateur, test_queryServlet_Bdd.motDePasse );
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
			   Connection connexion = DriverManager.getConnection( test_queryServlet_Bdd.url, test_queryServlet_Bdd.utilisateur, test_queryServlet_Bdd.motDePasse );
		       statement = connexion.createStatement();
		   } catch ( SQLException e ) {
			   System.out.println(e);
		   }
		   return statement;
	   }
	private int testInt(ResultSet resultat){
		int test=-1;
		try {
			while(resultat.next()){
				try {
					test = resultat.getInt(1);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return test;
	}
}

