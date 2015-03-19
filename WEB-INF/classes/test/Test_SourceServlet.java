package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sourceServlet.SourceServlet;
import baseDeDonnees.Bdd;
import junit.framework.TestCase;

public class Test_SourceServlet extends TestCase {

private Bdd bdd;
	
	@Before
	public void setUp() throws Exception {
		this.bdd = new Bdd();
		this.bdd.connexionBdd();
	}

	@After
	public void tearDown() throws Exception {
		this.bdd.closeConnexion();
		this.bdd = null;
	}
	
	@Test
	public void test_creerId() {
		int id = SourceServlet.creerId(bdd, "session", "ID_Session");
		if (id > 0 && id < 10000){
			System.out.println("L'id " + id + " est conforme.");
			assertTrue(true);
		} else assertTrue (false);
	}
}