package test;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import baseDeDonnees.Bdd;
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

	private Vote v;
	private ArrayList <String> valeurs;
	private ArrayList<String> typeValeurs;
	private Bdd bdd;
	private int idVote;
	private int valeurDuVote = 10;
	
	@Before
	public void setUp() throws Exception {

		this.valeurs = new ArrayList<String>();
		this.valeurs.add("1245"); //id_session
		this.valeurs.add("1566"); //id_etudiant
		this.typeValeurs = new ArrayList<String>();
		this.typeValeurs.add("int");
		this.typeValeurs.add("int");
			
		this.v = new Vote(valeurs, typeValeurs);
		
		this.bdd = new Bdd();
		this.bdd.connexionBdd();
		
		this.idVote = this.v.ajouterVote(bdd, valeurDuVote+"");
	}

	@After
	public void tearDown() throws Exception {
		this.bdd.closeConnexion();
		this.bdd = null;
		this.v = null;
	}

	@Test
	public void test_ajouterVote() {
		String requete = "select * from vote where ID_Vote = " + this.idVote + ";";
		ResultSet resultat = bdd.faireSelect(requete);
		int idVote=0;
		int valeur=0;
		int idSession=0;
		int idEtudiant=0;

		try {
			if (resultat.next()){
				idVote = resultat.getInt("ID_Vote");
				valeur = resultat.getInt("valeur");
				idSession = resultat.getInt("ID_Session"); 
				idEtudiant = resultat.getInt("ID_Etudiant"); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("idVote = " + idVote + " valeur = " + valeur + 
				" idsession = " + idSession + " idetudiant = " + idEtudiant );
		
		assertEquals(this.idVote, idVote);
		assertEquals(this.valeurDuVote, valeur);
		assertEquals(this.valeurs.get(0), idSession+"");
		assertEquals(this.valeurs.get(1), idEtudiant+"");
	}
}
