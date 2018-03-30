package jee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.jws.WebService;



@WebService(targetNamespace = "http://jee/", endpointInterface = "jee.GestionVisiteSEI", portName = "GestionVisitePort", serviceName = "GestionVisiteService")
public class GestionVisite implements GestionVisiteSEI  {
	
	public ArrayList<Visite> trouverVisite(Visite uneVisite){
		
		ArrayList<Visite> listeVisite = new ArrayList<Visite>();
	
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection db = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionvisites?user=root&password=");
			Statement stmt = db.createStatement();
			
			String cdtTypeVisite =  uneVisite.getTypeVisite();
			String cdtVille = uneVisite.getVille();
			String cdtDateVisite = uneVisite.getDateVisite();
			String cdtPrixVisite = "<="+uneVisite.getPrixVisite();
			
			String requete = "SELECT * FROM Visite";
			int nombreDeAnd = 0;
			
			requete = miseAJourRequete(uneVisite, requete, nombreDeAnd, cdtTypeVisite, cdtVille, cdtDateVisite, cdtPrixVisite);
			stmt.executeQuery(requete);
			ResultSet rset = stmt.getResultSet();			
			
			// Creer une liste comportant toutes les visites de la base de donnees
			while(rset.next()) {
				Visite visite = new Visite();
				visite.setDateVisite((rset.getString("dateVisite")).toString());
				visite.setIdVisite(Integer.parseInt(rset.getString("idVisite")));
				visite.setPrixVisite(Integer.parseInt(rset.getString("prixVisite")));
				visite.setTypeVisite((rset.getString("typeVisite")).toString());
				visite.setVille((rset.getString("ville")).toString());
				listeVisite.add(visite);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeVisite;
				
	}
	
	public int reserverVisite(ReservationVisite uneReservation)
	{
		int codeReservation = -1;
		
		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection db = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionvisites?user=root&password=");
			Statement stmt = db.createStatement();
			
			stmt.executeUpdate("INSERT INTO reservation(idVisite, idClient, nombreplaces, booleanPaiementEffectue) "
					+ "VALUES( '" + uneReservation.getIdVisite() + "', '" + uneReservation.getIdClient() +
					"', '" + uneReservation.getNombrePlace() + "', 0)");
			
			stmt.executeQuery("SELECT MAX(idReservation) FROM reservation");
			ResultSet rset = stmt.getResultSet();
			
			while(rset.next())
			{
				codeReservation = rset.getInt(1);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return codeReservation;
	}
	
	public String payerVisite(int codeReservation)
	{
		String message = "Le paiement de votre visite n'a pas pu etre effectue !";
		
		try
		{
			int booleen = -1;
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection db = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionvisites?user=root&password=");
			Statement stmt = db.createStatement();
			
			stmt.executeQuery("SELECT booleanPaiementEffectue FROM reservation "
					+ "WHERE idReservation = '" + codeReservation + "'");
			ResultSet rset = stmt.getResultSet();
			
			while(rset.next())
			{
				booleen = rset.getInt(1);
			}
			rset.close();
			if(booleen == 1)
			{
				message = "Le paiement a deja ete effectu� !";
			}
			else if(booleen == 0)
			{
				stmt.executeUpdate("UPDATE reservation SET booleanPaiementEffectue = '1'"
						+ "WHERE idReservation ='" + codeReservation + "'");
				message = "Le paiement de votre visite a ete correctement effectue !";
			}
			return message;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return message;
		}
	}
	

	
	public boolean annulerVisite(int codeReservation) {
		
		int idClient = 0;
		boolean visiteAnnulee = false;
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection db = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionvisites?user=root&password=");
			Statement stmt = db.createStatement();
			stmt.executeQuery("Select * from Reservation WHERE idReservation ='" + codeReservation + "'");
			ResultSet rset = stmt.getResultSet();
			
			while(rset.next()) {
				idClient = (Integer.parseInt(rset.getString("idClient")));
			}
			
			
			if(idClient != 0) {
				stmt.executeUpdate("DELETE FROM Reservation WHERE idClient ='"+ idClient +"'" );
				visiteAnnulee = true;
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return visiteAnnulee;
	}
	
	public String miseAJourRequete(Visite uneVisite, String requete, int nombreDeAnd, String cdtTypeVisite, String cdtVille, String cdtDateVisite, String cdtPrixVisite) {
		
		if(!uneVisite.getDateVisite().equals("none") || !uneVisite.getTypeVisite().equals("none") || uneVisite.getPrixVisite() != 0 || !uneVisite.getVille().equals("none")) {
			requete = requete + " WHERE";
		}
		
		// Type des visites //
		if(uneVisite.getTypeVisite().equals("none")) {	
			cdtTypeVisite = "";
		} else {
			requete = requete + " typeVisite = '"+ cdtTypeVisite +"'";
			nombreDeAnd = 1;
		}
		
		//  Ville des visites //
		if(uneVisite.getVille().equals("none")) {	
			cdtVille = "";
		} else {
			if(nombreDeAnd == 1) {
				requete = requete + " and ville = '"+ cdtVille +"'";
				nombreDeAnd = 1;
			} else {
				requete = requete + " ville = '"+ cdtVille +"'";
				nombreDeAnd = 1;
			}
		}
		
		//  Date des visites //
		if(uneVisite.getDateVisite().equals("none")) {
			cdtDateVisite = "";
		} else {
			if(nombreDeAnd == 1 || nombreDeAnd == 2) {
				requete = requete + " and dateVisite >= '"+ cdtDateVisite +"'";
				nombreDeAnd = 1;
			} else {
				requete = requete + " dateVisite >= '"+ cdtDateVisite +"'";
				nombreDeAnd = 1;
			}
		}
		
		// Prix des visites //
		if(uneVisite.getPrixVisite() == 0) {	
			cdtPrixVisite = "";
			nombreDeAnd = 0;
		} else {
			if(nombreDeAnd == 1 || nombreDeAnd == 2 || nombreDeAnd == 3) {
				requete = requete + " and prixVisite "+ cdtPrixVisite +"";
				nombreDeAnd = 0;
			} else {
				requete = requete + " prixVisite "+ cdtPrixVisite +"";
				nombreDeAnd = 0;
			}
		}
		return requete;
	}
}
	
