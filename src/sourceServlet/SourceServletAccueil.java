package sourceServlet;

import javax.servlet.http.HttpSession;

import baseDeDonnees.Bdd;

public class SourceServletAccueil {
	
	public static int creerIdSession(Bdd bdd) {
		return SourceServlet.creerId(bdd, "session", "ID_Session");
	}
	
	//retourne l'id de la nouvelle session et supprime l'encienne
	public static int supprimerSession (Bdd baseDonnee, HttpSession session){
		SuppressionSession s;
		s = new SuppressionSession (baseDonnee, session.getAttribute("identifiant").toString());
		return s.supprimerSession ();
	}
	
	
}


