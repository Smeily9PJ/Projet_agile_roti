package sourceServlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import baseDeDonnees.Bdd;

public class SourceServlet {

	public static int creerId(Bdd bdd, String nomTable, String nomIdentifiant) {
		Random rnd = new Random();
		ResultSet resultat = null;
		boolean trouve = false;
		int id;
		do {
			id = rnd.nextInt(10000);
			try {
				resultat = bdd.faireSelect("SELECT *  FROM " + nomTable
						+ " where " + nomIdentifiant + " = " + id + " ;");
				if (!resultat.next()) {
					trouve = true;
				}
			} catch (SQLException e) {
				System.out.println(e);
			}
		} while (!trouve);
		return id;
	}
}
