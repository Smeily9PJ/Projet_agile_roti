package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sourceServlet.Humeur;
import baseDeDonnees.Bdd;

/***
 * Necessite
 * identifiantEtudiant = 200
 * emotion "colere";
 * 		INSERT INTO Etudiant values (200, "colere");
 * identifiantEtudiant = 201
 * emotion "blaze";
 * 		INSERT INTO Etudiant values (201, "blaze");
 * identifiantEtudiant = 202
 * emotion "dort";
 * 		INSERT INTO Etudiant values (202, "dort");
 * identifiantEtudiant = 203
 * emotion "content";
 * 		INSERT INTO Etudiant values (203, "content");
 * identifiantEtudiant = 204
 * emotion "triste";
 * 		INSERT INTO Etudiant values (204, "triste");
 * identifiantEtudiant = 205
 * emotion "rigole";
 * 		INSERT INTO Etudiant values (205, "rigole");
 */

public class Test_Humeur {

	private Bdd bdd;
	
	private Humeur humeurColere;
	private Humeur humeurBlaze;
	private Humeur humeurDort;
	private Humeur humeurContent;
	private Humeur humeurTriste;
	private Humeur humeurRigole;
	
	private int ETUDIANT_COLERE = 200;
	private int ETUDIANT_BLAZE = 201;
	private int ETUDIANT_DORT = 202;
	private int ETUDIANT_CONTENT = 203;
	private int ETUDIANT_TRISTE = 204;
	private int ETUDIANT_RIGOLE = 205;
	
	@Before
	public void setUp() throws Exception {
		this.bdd = new Bdd();
		this.bdd.connexionBdd();
		this.humeurColere = new Humeur (ETUDIANT_COLERE, this.bdd);
		this.humeurBlaze = new Humeur (ETUDIANT_BLAZE, this.bdd);
		this.humeurDort = new Humeur (ETUDIANT_DORT, this.bdd);
		this.humeurContent = new Humeur (ETUDIANT_CONTENT, this.bdd);
		this.humeurTriste = new Humeur (ETUDIANT_TRISTE, this.bdd);
		this.humeurRigole = new Humeur (ETUDIANT_RIGOLE, this.bdd);
	}

	@After
	public void tearDown() throws Exception {
		this.bdd.closeConnexion();
		this.bdd = null;
		this.humeurColere = null;
		this.humeurBlaze = null;
		this.humeurDort = null;
		this.humeurContent = null;
		this.humeurTriste = null;
		this.humeurRigole = null;
	}

	@Test
	public void trouverHumeur_colere() {
		assertEquals ("colere", this.humeurColere.trouverHumeur());
	}
	
	@Test
	public void trouverHumeur_blaze() {
		assertEquals ("blaze", this.humeurBlaze.trouverHumeur());
	}
	
	@Test
	public void trouverHumeur_dort() {
		assertEquals ("dort", this.humeurDort.trouverHumeur());
	}
	
	@Test
	public void trouverHumeur_content() {
		assertEquals ("content", this.humeurContent.trouverHumeur());
	}
	
	@Test
	public void trouverHumeur_triste() {
		assertEquals ("triste", this.humeurTriste.trouverHumeur());
	}
	
	@Test
	public void trouverHumeur_rigole() {
		assertEquals ("rigole", this.humeurRigole.trouverHumeur());
	}

}
