package jee;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Test;

public class GestionVisiteTest {


		@Test
		public void testTrouverVisitePresente() {
			GestionVisite gp = new GestionVisite();
			ArrayList<Visite> resultatExpected = new ArrayList<>();
			Visite visiteTest = new Visite();
			visiteTest.setVille("Toulouse");
			visiteTest.setPrixVisite(45);
			visiteTest.setTypeVisite("Culturel");
			visiteTest.setDateVisite("2018-04-19");
			resultatExpected = gp.trouverVisite(visiteTest);
			
			assertEquals(1, resultatExpected.size());
			assertEquals("Culturel", resultatExpected.get(0).getTypeVisite());
			assertEquals("Toulouse", resultatExpected.get(0).getVille());
			assertEquals(45, resultatExpected.get(0).getPrixVisite());
			
		}
		
		@Test
		public void testTrouverVisiteNonPresente() {
			GestionVisite gp = new GestionVisite();
			ArrayList<Visite> resultatExpected = new ArrayList<>();
			Visite visiteTest = new Visite();
			visiteTest.setVille("Angers");
			visiteTest.setPrixVisite(45);
			visiteTest.setTypeVisite("Culturel");
			visiteTest.setDateVisite("2018-04-19");
			resultatExpected = gp.trouverVisite(visiteTest);
			
			assertSame(0,resultatExpected.size());
		}
		
		@Test
		public void testReserverVisite() {
			GestionVisite gp = new GestionVisite();
			ReservationVisite reservationTest = new ReservationVisite();
			Client clientTest = new Client();
			Visite visiteTest = new Visite();
			
			visiteTest.setVille("Toulouse");
			visiteTest.setPrixVisite(45);
			visiteTest.setTypeVisite("Culturel");
			visiteTest.setDateVisite("2018-04-19");
			visiteTest.setIdVisite(4);
			clientTest.setId(1);
			
			reservationTest.setIdVisite(visiteTest.getIdVisite());
			reservationTest.setIdClient(clientTest.getId());
			
			gp.reserverVisite(reservationTest);
			
			assertEquals(105, reservationTest.getIdReservation());		
		
		}
		
		@Test
		public void testReserverVisiteDejaReservee() {
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
