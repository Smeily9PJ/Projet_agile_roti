package queryServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Bdd { 
	 	private  Connection connexion = null;
	 	private  Statement statement = null;
	 	private  ResultSet resultat = null;
	   
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
	
		public ResultSet getResultat() {
			return resultat;
		}
		
		public ResultSet faireSelect(String requete){
			try {
				resultat = statement.executeQuery(requete );
			} catch (SQLException e) {
				System.out.println(e);
			}
			return this.resultat;
		}
		
		public int faireInsert(String requete){
			int resultat = 0 ;
			try {
				resultat = statement.executeUpdate( requete );
				 if(resultat ==0){
			    	   System.out.println("echec inert");
			       }
			       if(resultat ==1){
			    	   System.out.println("reussite insert");
			       }
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return resultat;
		}
		
	   public void closeConnexion(){
		   if ( resultat != null ) {
		        try {
		            /* On commence par fermer le ResultSet */
		            resultat.close();
		        } catch ( SQLException ignore ) {
		        }
		    }
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
