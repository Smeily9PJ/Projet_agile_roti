package sourceServlet;

import baseDeDonnees.Bdd;

public class SourceServletVoteEtudiant {
	
	public static int creerIdVote(Bdd bdd) {
		return SourceServlet.creerId(bdd, "vote", "ID_Vote");
	}
	
}