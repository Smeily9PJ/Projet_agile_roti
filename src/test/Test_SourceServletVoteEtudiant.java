package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sourceServlet.SourceServletVoteEtudiant;

public class Test_SourceServletVoteEtudiant {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_afficherNumeroHumeur_colere() {
		assertEquals(1, SourceServletVoteEtudiant.afficherNumeroHumeur("colere"));
	}
	
	@Test
	public void test_afficherNumeroHumeur_triste() {
		assertEquals(2, SourceServletVoteEtudiant.afficherNumeroHumeur("triste"));
	}
	
	@Test
	public void test_afficherNumeroHumeur_blaze() {
		assertEquals(3, SourceServletVoteEtudiant.afficherNumeroHumeur("blaze"));
	}
	
	@Test
	public void test_afficherNumeroHumeur_dort() {
		assertEquals(4, SourceServletVoteEtudiant.afficherNumeroHumeur("dort"));
	}
	
	@Test
	public void test_afficherNumeroHumeur_rigole() {
		assertEquals(5, SourceServletVoteEtudiant.afficherNumeroHumeur("rigole"));
	}
	
	@Test
	public void test_afficherNumeroHumeur_content() {
		assertEquals(6, SourceServletVoteEtudiant.afficherNumeroHumeur("content"));
	}
	
}
