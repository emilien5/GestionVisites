package jee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GestionVisite {
	
	public ArrayList<Visite> trouverVisite(Visite uneVisite){
		
		ArrayList<Visite> listeVisite = new ArrayList<Visite>();
		ArrayList<Visite> listeVisiteParCategorie = new ArrayList<Visite>();
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection db = DriverManager.getConnection("jdbc:mysql://localhost/GestionVisite?user=root&password=network");
			Statement stmt = db.createStatement();
			stmt.executeQuery("Select * from Visite");
			ResultSet rset = stmt.getResultSet();
			
			
			while(rset.next()) {
				Visite visite = new Visite();
				visite.setDateVisite(("dateVisite").toString());
				visite.setIdVisite(Integer.parseInt("idVisite"));
				visite.setPrixVisite(Integer.parseInt("prixVisite"));
				visite.setTypeVisite(("typeVisite").toString());
				visite.setVille(("ville").toString());
				listeVisite.add(visite);
			}
			
			for(int i=0; i<listeVisite.size()-1; i++) {
				if(listeVisite.get(i).getTypeVisite() == uneVisite.getTypeVisite() ||
						listeVisite.get(i).getDateVisite() == uneVisite.getDateVisite() ||
						listeVisite.get(i).getVille() == uneVisite.getVille()) {
					listeVisiteParCategorie.add(listeVisite.get(i));
				}
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listeVisiteParCategorie;
				
	}
}
	
