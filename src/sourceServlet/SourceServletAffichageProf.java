package sourceServlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import servlet.ServletAffichageProf;
import baseDeDonnees.Bdd;

public class SourceServletAffichageProf {

	private final static ArrayList<String> TYPE_VALEUR = new ArrayList<String>() {
		private static final long serialVersionUID = -2288323471518509226L;

		{
			add("int");
			add("String");
			add("int");
		}
	};

	private Bdd bdd;
	private String mdp;
	private HttpSession session;
	private HttpServletRequest requete;
	
	public SourceServletAffichageProf(HttpServletRequest request,
			ServletAffichageProf servletActuelle) {
		this.session = request.getSession();
		this.mdp = request.getParameter("accueil_text_mdpSession");
		this.requete = request;
		this.bdd = new Bdd();
		bdd.connexionBdd();
	}

	protected void finalize() {
		this.bdd.closeConnexion();
	}

	public void creationDonneesInsertProfesseur() {
		ArrayList<String> valeurs = new ArrayList<String>();
		valeurs.add(this.session.getAttribute("identifiant").toString());
		valeurs.add(this.session.getAttribute("mdp").toString());
		valeurs.add(this.session.getAttribute("timing").toString());

		bdd.faireInsert(
				"insert into session (ID_Session, password, interval_Vote) values (?, ?, ?);",
				valeurs, SourceServletAffichageProf.TYPE_VALEUR);
	}

	public void detailSession() {
		this.session.setAttribute("mdp", mdp);
		this.session.setAttribute("timing",
				this.requete.getParameter("numeric"));
	}
	

	public static String calculHumeurMajoritaire (Bdd bdd, String idSession){
		HumeurMajoritaire humeurM = new HumeurMajoritaire(bdd, idSession);
		return humeurM.toString();
	}

}
