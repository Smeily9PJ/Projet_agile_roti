package queryServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Bdd { 
	 	private  Connection connexion = null;
	 	private  Statement statement = null;
	   
	   public void connexionBdd(){
		   try {
			    Class.forName( "com.mysql.jdbc.Driver" );
			} catch ( ClassNotFoundException e ) {
			   System.out.println(e);
			}
		   /* Connexion à la base de données */
		   String url = "jdbc:mysql://localhost:8888/bddroti";
		   String utilisateur = "root";
		   String motDePasse = "root";
		   try {
		       connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
		       /* Création de l'objet gérant les requêtes */
		       statement = connexion.createStatement();
		   } catch ( SQLException e ) {
			   System.out.println(e);
		   }
	   }
	   
		public Connection getConnexion() {
			return connexion;
		}
	
		public Statement getStatement() {
			return statement;
		}
	
		public ResultSet faireSelect(String requete){
			ResultSet resultat = null;
			try {
				resultat = statement.executeQuery(requete );
			} catch (SQLException e) {
				System.out.println(e);
			}
			return resultat;
		}
		public ResultSet faireSelectParam(String requete, ArrayList<String> valeurs, ArrayList<String> typeValeur){
			ResultSet resultat = null;
			if(valeurs.size() == typeValeur.size()){
				PreparedStatement preparedStatement;
				try {
					preparedStatement = this.getConnexion().prepareStatement(requete);
					for(int i = 0; i< typeValeur.size(); i++){
						switch(typeValeur.get(i)){
						case "int":preparedStatement.setInt(i+1, Integer.parseInt(valeurs.get(i)));
							break;
						case "String" :preparedStatement.setString(i+1, valeurs.get(i));
							break;
						}
					}				
					resultat = preparedStatement.executeQuery();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}else{
				System.out.println("erreur : tailles listes différentes pour le delete !!!!! valeurs : " +valeurs.size()+" types : " +typeValeur.size() );
			}
			return resultat;
		}
		
		public void faireDelete(String requete, ArrayList<String> valeurs, ArrayList<String> typeValeur){
			if(valeurs.size() == typeValeur.size()){
				PreparedStatement preparedStatement;
				try {
					preparedStatement = this.getConnexion().prepareStatement(requete);
					for(int i = 0; i< typeValeur.size(); i++){
						switch(typeValeur.get(i)){
						case "int":preparedStatement.setInt(i+1, Integer.parseInt(valeurs.get(i)));
							break;
						case "String" :preparedStatement.setString(i+1, valeurs.get(i));
							break;
						}
					}				
					int resultat = preparedStatement.executeUpdate();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}else{
				System.out.println("erreur : tailles listes différentes pour le delete !!!!! valeurs : " +valeurs.size()+" types : " +typeValeur.size() );
			}
		}
		
		public void faireInsert(String requete, ArrayList<String> valeurs, ArrayList<String> typeValeur){
			if(valeurs.size() == typeValeur.size()){
				PreparedStatement preparedStatement;
				try {
					preparedStatement = this.getConnexion().prepareStatement(requete);
					for(int i = 0; i< typeValeur.size(); i++){
						switch(typeValeur.get(i)){
						case "int":preparedStatement.setInt(i+1, Integer.parseInt(valeurs.get(i)));
							break;
						case "String" :preparedStatement.setString(i+1, valeurs.get(i));
							break;
						}
					}				
					int resultat = preparedStatement.executeUpdate();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}else{
				System.out.println("erreur : tailles listes différentes pour l'insert !!!!! valeurs : " +valeurs.size()+" types : " +typeValeur.size() );
			}
		}
		
	   public void closeConnexion(){
		     if ( statement != null ) {
		        try {
		            /* Puis on ferme le Statement */
		            statement.close();
		        } catch ( SQLException ignore ) {
		        }
		    }
		    if ( connexion != null ) {
		        try {
		            /* Et enfin on ferme la connexion */
		            connexion.close();
		        } catch ( SQLException ignore ) {
		        }
		    }
	   }
	   
	}
