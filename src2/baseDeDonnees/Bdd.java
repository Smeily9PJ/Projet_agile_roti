package baseDeDonnees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Bdd {
	private static final String DRIVER_JDBC = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:8888/bddroti";
	private static final String UTILISATEUR = "root";
	private static final String MOT_DE_PASSE = "root";

	private Connection connexion = null;
	private Statement statement = null;

	public void connexionBdd() {
		try {
			Class.forName(Bdd.DRIVER_JDBC);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Erreur: Le driver " + Bdd.DRIVER_JDBC
					+ " n'est pas reconnu.");
		}
		this.connexion = this.requeteDeConnexion();
		this.statement = this.requeteDeStatement();

	}

	public Connection getConnexion() {
		return connexion;
	}

	public Statement getStatement() {
		return statement;
	}

	public ResultSet faireSelect(String requete) {
		ResultSet resultat = null;
		try {
			resultat = statement.executeQuery(requete);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return resultat;
	}

	public ResultSet faireSelectParam(String requete,
			ArrayList<String> valeurs, ArrayList<String> typeValeur) {
		ResultSet resultat = null;
		if (valeurs.size() == typeValeur.size()) {
			PreparedStatement preparedStatement = initialisationStatement(
					requete, valeurs, typeValeur);
				try {
					resultat = preparedStatement.executeQuery();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		} else {
			System.out.println("erreur : tailles listes différentes pour le delete !!!!! valeurs : "
							+ valeurs.size() + " types : " + typeValeur.size());
		}
		return resultat;
	}

	public void faireDelete(String requete, ArrayList<String> valeurs,
			ArrayList<String> typeValeur) {
		if (valeurs.size() == typeValeur.size()) {
			PreparedStatement preparedStatement = initialisationStatement(
					requete, valeurs, typeValeur);
			try {
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("erreur : tailles listes différentes pour le delete !!!!! valeurs : "
							+ valeurs.size() + " types : " + typeValeur.size());
		}
	}

	public int faireInsert(String requete, ArrayList<String> valeurs,
			ArrayList<String> typeValeur) {
		int resultat = 0;
		if (valeurs.size() == typeValeur.size()) {
			PreparedStatement preparedStatement = initialisationStatement(
					requete, valeurs, typeValeur);
			try {
				resultat = preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Erreure L'update de la BDD n'a pas pu s'effectuer");
			}
		} else {
			System.out.println("erreur : tailles listes différentes pour l'insert !!!!! valeurs : "
							+ valeurs.size() + " types : " + typeValeur.size());
		}
		return resultat;
	}

	public void closeConnexion() {
		requeteCloseStatement();
		requeteCloseConnection();
	}

	//////////////////////
	// 					//
	// CLASSES PRIVATES //
	// 					//
	//////////////////////

	private void requeteCloseConnection() {
		if (connexion != null) {
			try {
				connexion.close();
				connexion = null;
			} catch (SQLException ignore) {
			}
		}
	}

	private void requeteCloseStatement() {
		if (statement != null) {
			try {
				statement.close();
				statement = null;
			} catch (SQLException ignore) {
			}
		}
	}

	private Connection requeteDeConnexion() {
		Connection c = null;
		try {
			c = DriverManager.getConnection(Bdd.URL, Bdd.UTILISATEUR,
					Bdd.MOT_DE_PASSE);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur: La tentative de connection à la base de données a échoué dans requeteDeConnexion");
		}
		return c;
	}

	private Statement requeteDeStatement() {
		Statement s = null;
		try {
			s = connexion.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out
					.println("La creation du statement a échoué dans la requeteDeStatement");
		}
		return s;
	}

	private void requetePreparationStatementInt(
			PreparedStatement preparedStatement, String donnee, int i) {
		try {
			preparedStatement.setInt(i + 1, Integer.parseInt(donnee));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void requetePreparationStatementString(
			PreparedStatement preparedStatement, String donnee, int i) {
		try {
			preparedStatement.setString(i + 1, donnee);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private PreparedStatement initialisationStatement(String requete,
			ArrayList<String> valeurs, ArrayList<String> typeValeur) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.getConnexion().prepareStatement(requete);
			for (int i = 0; i < typeValeur.size(); i++) {
				switch (typeValeur.get(i)) {
				case "int": requetePreparationStatementInt(preparedStatement,
							valeurs.get(i), i); break;
				case "String":requetePreparationStatementString(preparedStatement,
							valeurs.get(i), i); break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preparedStatement;
	}

}
