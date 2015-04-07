package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sourceServlet.HumeurMajoritaire;
import baseDeDonnees.Bdd;

/***
 * Necessite
 * Table Etudiant :
 * 		INSERT INTO Etudiant values (200, "colere");
 * 		INSERT INTO Etudiant values (201, "blaze");
 * 		INSERT INTO Etudiant values (202, "dort");
 * 		INSERT INTO Etudiant values (203, "content");
 * 		INSERT INTO Etudiant values (204, "triste");
 * 		INSERT INTO Etudiant values (205, "rigole");
 * 		INSERT INTO Etudiant values (206, "colere");
 * Table Session :
 * 		INSERT INTO Session values (300, "300", 1);
 * Table Vote :
 * 		INSERT INTO Vote values (400, 4, 300, 200);
 *      INSERT INTO Vote values (401, 4, 300, 201);
 *		INSERT INTO Vote values (402, 4, 300, 202);
 *		INSERT INTO Vote values (403, 4, 300, 203);
 *		INSERT INTO Vote values (404, 4, 300, 204);
 * 		INSERT INTO Vote values (405, 4, 300, 205);
 * 		INSERT INTO Vote values (406, 4, 300, 206);
 */

public class Test_HumeurMajoritaire {

	private final String ID_SESSION = "300";
	private HumeurMajoritaire humeurColere;
	private Bdd bdd;
	
	@Before
	public void setUp() throws Exception {
		this.bdd = new Bdd();
		this.bdd.connexionBdd();
		this.humeurColere = new HumeurMajoritaire (bdd, ID_SESSION);
	}

	@After
	public void tearDown() throws Exception {
		this.bdd.closeConnexion();
		this.bdd = null;
		this.humeurColere = null;
	}

	@Test
	public void test_getHumeurMajoritaire() {
		assertEquals ("colere", humeurColere.getHumeurMajoritaire());
	}
	
	@Test
	public void test_toString() {
		String attendu = "4.0&7&colere";
		assertEquals (attendu, humeurColere.toString());
	}

}
