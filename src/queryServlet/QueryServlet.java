package queryServlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class QueryServlet extends HttpServlet {  // JDK 6 and above only
 
   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
//      // Set the MIME type for the response message
//  
	  response.setContentType("text/html");
      // Get a output writer to write the response message into the network socket
	  PrintWriter out = response.getWriter();
     
	  out.println("Connexion... </br>");
  
	   
	   try {

		    Class.forName( "com.mysql.jdbc.Driver" );
		    out.println("connexion jdbc ok</br>");
		} catch ( ClassNotFoundException e ) {

		   out.println("erreur connexion jdbc</br>");

		}
	   
	   /* Connexion � la base de donn�es */
	   String url = "jdbc:mysql://localhost:8888/bddroti";
	   String utilisateur = "root";
	   String motDePasse = "root";
	   Connection connexion = null;
	   Statement statement = null;
	   ResultSet resultat = null;
	   try {
	       connexion = DriverManager.getConnection( url, utilisateur, motDePasse );

	       out.println("connexion bdd ok </br>");
	       /* Cr�ation de l'objet g�rant les requ�tes */
	       statement = connexion.createStatement();
	       out.println("statement ok</br>");

	       /* Ex�cution d'une requ�te de lecture */
	       resultat = statement.executeQuery( "SELECT *  FROM etudiant;" );
	       out.println("exec requete lecture ok</br>");
	       
	       
	       /* R�cup�ration des donn�es du r�sultat de la requ�te de lecture */
	        while ( resultat.next() ) {
	            int idUtilisateur = resultat.getInt( "ID_Etudiant" );
	            
	            /* Formatage des donn�es pour affichage dans la JSP finale. */
	            out.println( "Donn�es retourn�es par la requ�te : id = " + idUtilisateur + "." );
	        }
	       
	       
	       /* Ex�cution d'une requ�te d'�criture */
	      int statut = statement.executeUpdate( "INSERT into etudiant values (1);" );
	       out.println("exec requete ecriture ok</br>");
	       if(statut ==0){
	    	   out.println("echec insert </br>");
	       }
	       if(statut ==1){
	    	   out.println("reussite insert </br>");
	       }
	       /* Ex�cution d'une requ�te de lecture */
	       resultat = statement.executeQuery( "SELECT *  FROM etudiant;" );
	       out.println("exec requete lecture ok</br>");
	       
	       
	       /* R�cup�ration des donn�es du r�sultat de la requ�te de lecture */
	        while ( resultat.next() ) {
	            int idUtilisateur = resultat.getInt( "ID_Etudiant" );
	            
	            /* Formatage des donn�es pour affichage dans la JSP finale. */
	            out.println( "Donn�es retourn�es par la requ�te : id = " + idUtilisateur + "." );
	        }
	       
	   } catch ( SQLException e ) {
		   out.println("erreur connexion</br>");
	   } finally {
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
}