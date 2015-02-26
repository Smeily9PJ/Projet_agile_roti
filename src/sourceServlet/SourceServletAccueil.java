package sourceServlet;

import baseDeDonnees.Bdd;

public class SourceServletAccueil {
	
	public static int creerIdSession(Bdd bdd) {
		return SourceServlet.creerId(bdd, "session", "ID_Session");
	}
}


