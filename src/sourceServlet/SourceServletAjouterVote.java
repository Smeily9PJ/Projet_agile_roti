package sourceServlet;

import baseDeDonnees.Bdd;

public class SourceServletAjouterVote {
	
	public static int creerIdSession(Bdd bdd) {
		return SourceServlet.creerId(bdd, "vote", "ID_Vote");
	}
	
}