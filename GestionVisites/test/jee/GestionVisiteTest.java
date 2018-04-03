package jee;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Test;

import junit.framework.Assert;

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
			
			assertEquals(105, gp.reserverVisite(reservationTest));		
		
		}
		
	
		@Test
		public void testPayerVisite() {
				int idReservation = 103;
				GestionVisite gp = new GestionVisite();				
								
				assertEquals("Le paiement de votre visite a ete correctement effectue !", gp.payerVisite(idReservation));		
		}
		
		@Test
		public void testPayerVisiteDejaPayee() {
			int idReservation = 102;
			GestionVisite gp = new GestionVisite();	
			
			assertEquals("Le paiement a deja ete effectué !", gp.payerVisite(idReservation));
		}
		
		@Test
		public void testAnnulerVisite() {
			int idReservation = 102;
			GestionVisite gp = new GestionVisite();	
			
			assertEquals(true, gp.annulerVisite(idReservation));
		}
		
		@Test
		public void testAnnulerVisiteNonPresente() {
			int idReservation = 102;
			GestionVisite gp = new GestionVisite();	
			
			assertEquals(false, gp.annulerVisite(idReservation));
		}
		
		
}
