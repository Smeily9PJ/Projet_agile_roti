package sourceServlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import baseDeDonnees.Bdd;

public class SourceServletAccueil {
	
	public static void creerIdSession(HttpServletRequest request){
		HttpSession session = request.getSession();
		Random rnd = new Random();
		ResultSet resultat = null;
		Bdd bdd = new Bdd();
		bdd.connexionBdd();
		boolean trouve = false;
		int id;
		
		
		do{
			id= rnd.nextInt(10000);
			try {
				resultat = bdd.faireSelect( "SELECT *  FROM session where ID_Session = "+ id +" ;" );
				if(!resultat.next()){
					trouve = true;
				}
			} catch (SQLException e) {
				System.out.println(e);
			}
		}while(!trouve);
		
		bdd.closeConnexion();
		session.setAttribute("identifiant",id);
	}
}