package jee;

public class GestionVisitesTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GestionVisite gp = new GestionVisite();
		Visite uneVisite = new Visite();
		uneVisite.setTypeVisite("none");
		uneVisite.setDateVisite("2018-04-25");
		uneVisite.setPrixVisite(0);
		uneVisite.setVille("Najac");
		gp.trouverVisite(uneVisite); 
		//ReservationVisite rv = new ReservationVisite();
//		rv.setIdClient(4);
//		rv.setIdVisite(4);
//		gp.reserverVisite(rv);
//		gp.payerVisite(108);
	}

}
