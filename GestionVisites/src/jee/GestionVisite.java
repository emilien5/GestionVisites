package jee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.jws.WebService;

@WebService(targetNamespace = "http://jee/", endpointInterface = "jee.GestionVisitesSEI", portName = "GestionVisitePort", serviceName = "GestionVisiteService")
public class GestionVisite implements GestionVisitesSEI {
	
	public ArrayList<Visite> trouverVisite(Visite uneVisite){
		
		ArrayList<Visite> listeVisite = new ArrayList<Visite>();
		ArrayList<Visite> listeVisiteParCategorie = new ArrayList<Visite>();
	
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection db = DriverManager.getConnection("jdbc:mysql://localhost/gestionvisites?user=root&password=");
			Statement stmt = db.createStatement();
			stmt.executeQuery("Select * from Visite");
			ResultSet rset = stmt.getResultSet();
			
			// Creer une liste comportant toutes les visites de la base de données
			while(rset.next()) {
				Visite visite = new Visite();
				visite.setDateVisite((rset.getString("dateVisite")).toString());
				visite.setIdVisite(Integer.parseInt(rset.getString("idVisite")));
				visite.setPrixVisite(Integer.parseInt(rset.getString("prixVisite")));
				visite.setTypeVisite((rset.getString("typeVisite")).toString());
				visite.setVille((rset.getString("ville")).toString());
				System.out.println(rset.getString("ville"));
				listeVisite.add(visite);
			}
			

			for(int i=0; i<listeVisite.size(); i++) {
				if(listeVisite.get(i).getTypeVisite().equals(uneVisite.getTypeVisite()) ||
						listeVisite.get(i).getDateVisite().equals(uneVisite.getDateVisite()) ||
					listeVisite.get(i).getVille().equals(uneVisite.getVille())) {
					listeVisiteParCategorie.add(listeVisite.get(i));
				}

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(listeVisiteParCategorie);
		return listeVisiteParCategorie;
				
	}
	
	public int reserverVisite(ReservationVisite uneReservation)
	{
		int codeReservation = -1;
		
		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection db = DriverManager.getConnection("jdbc:mysql://localhost/gestionvisites?user=root&password=");
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
		String message = "Le paiement de votre visite n'a pas pu être effectué !";
		
		try
		{
			int booleen = -1;
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection db = DriverManager.getConnection("jdbc:mysql://localhost/gestionvisites?user=root&password=");
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
				message = "Le paiement a déjà été effectué !";
			}
			else if(booleen == 0)
			{
				stmt.executeUpdate("UPDATE reservation SET booleanPaiementEffectue = '1'"
						+ "WHERE idReservation ='" + codeReservation + "'");
				message = "Le paiement de votre visite a été correctement effectué !";
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
			Connection db = DriverManager.getConnection("jdbc:mysql://localhost/gestionvisites?user=root&password=");
			Statement stmt = db.createStatement();
			stmt.executeQuery("Select * from Reservation WHERE idReservation ='" + codeReservation + "'");
			ResultSet rset = stmt.getResultSet();
			
			while(rset.next()) {
				idClient = (Integer.parseInt(rset.getString("idClient")));
			}
			
			System.out.println(idClient);
			
			if(idClient != 0) {
				stmt.executeUpdate("DELETE FROM Reservation WHERE idClient ='"+ idClient +"'" );
				visiteAnnulee = true;
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return visiteAnnulee;
	}
}
	
