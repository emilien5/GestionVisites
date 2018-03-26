package jee;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class GestionVisitesTest {

	@Test
	public void testTrouverVisitePresente() {
		GestionVisite gp = new GestionVisite();
		ArrayList<Visite> resultatExpected = new ArrayList<>();
		Visite visiteTest = new Visite();
		visiteTest.setVille("Toulouse");
		resultatExpected = gp.trouverVisite(visiteTest);
		
		assertEquals("3", resultatExpected.size());
		assertEquals("Culturelle", resultatExpected.get(0).getTypeVisite());
		assertEquals("Toulouse", resultatExpected.get(0).getVille());
		assertEquals("45", resultatExpected.get(0).getPrixVisite());
		
		assertEquals("Gastronomique", resultatExpected.get(0).getTypeVisite());
		assertEquals("Toulouse", resultatExpected.get(0).getVille());
		assertEquals("105", resultatExpected.get(0).getPrixVisite());
		
		assertEquals("Familiale", resultatExpected.get(0).getTypeVisite());
		assertEquals("Toulouse", resultatExpected.get(0).getVille());
		assertEquals("245", resultatExpected.get(0).getPrixVisite());
	}
	
	@Test
	public void testTrouverVisiteNonPresente() {
		GestionVisite gp = new GestionVisite();
		ArrayList<Visite> resultatExpected = new ArrayList<>();
		Visite visiteTest = new Visite();
		visiteTest.setVille("Angers");
		resultatExpected = gp.trouverVisite(visiteTest);
		
		assertEquals("0", resultatExpected.size());
		assertEquals("", resultatExpected.get(0).getTypeVisite());
		assertEquals("", resultatExpected.get(0).getVille());
		assertEquals("", resultatExpected.get(0).getPrixVisite());
	}
	
	@Test
	public void testReserverVisitePresente() {
	}
	
	@Test
	public void testReserverVisiteNonPresente() {
	}
	
	@Test
	public void testPayerVisitePresente() {
	}
	
	@Test
	public void testPayerVisiteNonPresente() {
	}
	
	@Test
	public void testAnnulerVisitePresente() {
	}
	
	@Test
	public void testAnnulerVisiteNonPresente() {
	}
	
	
	
	
	
	


}

