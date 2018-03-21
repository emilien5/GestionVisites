package jee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GestionVisite {
	
	public static ArrayList<Visite> trouverVisite(Visite uneVisite){
		
		ArrayList<Visite> listeVisite = new ArrayList<Visite>();
		ArrayList<Visite> listeVisiteParCategorie = new ArrayList<Visite>();
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection db = DriverManager.getConnection("jdbc:mysql://localhost/GestionVisite?user=admin&password=network");
			Statement stmt = db.createStatement();
			stmt.executeQuery("Select * from Visite");
			ResultSet rset = stmt.getResultSet();
			
			
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(listeVisiteParCategorie);
		return listeVisiteParCategorie;
				
	}
	
	public static void main(String[] arg0) {
		Visite visite = new Visite();
		visite.setVille("Toulouse");
		trouverVisite(visite);
		annulerVisite(103);
	}
	
	public static String annulerVisite(int codeReservation) {
		
		int idClient = 0;
		String message = "";
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection db = DriverManager.getConnection("jdbc:mysql://localhost/GestionVisite?user=admin&password=network");
			Statement stmt = db.createStatement();
			stmt.executeQuery("Select * from Reservation WHERE idReservation ='" + codeReservation + "'");
			ResultSet rset = stmt.getResultSet();
			
			while(rset.next()) {
				idClient = (Integer.parseInt(rset.getString("idClient")));
			}
			
			System.out.println(idClient);
			
			if(idClient != 0) {
				stmt.executeUpdate("DELETE FROM Reservation WHERE idClient ='"+ idClient +"'" );
				message = "Vous avez annulé votre visite";
			}
			
			else {
				message = "Vous n'avez pas reservé de visite";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return message;
	}
}
	
