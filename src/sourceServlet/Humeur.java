package sourceServlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import baseDeDonnees.Bdd;

public class Humeur {
	
	private int idEtudiant;
	private Bdd bdd;
	private String humeur;
	private HashMap<String, Integer> listeHumeurs ;
	private ResultSet humeurEtudiant;

	public Humeur (int idEtudiant, Bdd bdd){
		this.idEtudiant = idEtudiant;
		this.bdd = bdd;
		this.humeur = "colere";
		this.listeHumeurs = new HashMap<String, Integer>();
		setHumeurEtudiant();
		initialisationListeHumeur();
		etudeHumeurEtudiant();
	}

	public String trouverHumeur() {
		Set<String> humeursPossibles = listeHumeurs.keySet();
		Iterator<String> iterHumeursPossibles = humeursPossibles.iterator();
		while (iterHumeursPossibles.hasNext()) {
			String humeurMap = (String) iterHumeursPossibles.next();
			if (listeHumeurs.get(humeur) <= listeHumeurs.get(humeurMap)) {
				humeur = humeurMap;
			}
		}
		return humeur;
	}
	
	private void setHumeurEtudiant (){
		String requeteSelectEtudiant = "select emotion from etudiant where ID_Etudiant=?";
		
		ArrayList<String> valeurs = new ArrayList<String>();
		valeurs.add(String.valueOf(idEtudiant));
		
		ArrayList<String> typeValeurs = new ArrayList<String>();
		typeValeurs.add("int");
		
		this.humeurEtudiant = bdd.faireSelectParam(requeteSelectEtudiant, valeurs, typeValeurs);
	}
	
	//Met a jour le premier argument de listeHumeurs
	private void initialisationListeHumeur (){
		this.listeHumeurs.put("colere", 0);
		this.listeHumeurs.put("blaze", 0);
		this.listeHumeurs.put("dort", 0);
		this.listeHumeurs.put("content", 0);
		this.listeHumeurs.put("triste", 0);
		this.listeHumeurs.put("rigole", 0);
	}
	
	//Met a jour le deuxieme argument de listeHumeurs 
	private void etudeHumeurEtudiant (){
		try {
			while (humeurEtudiant.next()) {
				listeHumeurs.put(humeurEtudiant.getString("emotion"), listeHumeurs
								.get(humeurEtudiant.getString("emotion")) + 1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
