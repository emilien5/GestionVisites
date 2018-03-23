package jee;

import java.util.ArrayList;

import javax.jws.WebService;

@WebService(name = "GestionVisitesSEI", targetNamespace = "http://jee/")
public interface GestionVisitesSEI {

	ArrayList<Visite> trouverVisite(Visite uneVisite);

	int reserverVisite(ReservationVisite uneReservation);

	String payerVisite(int codeReservation);

	boolean annulerVisite(int codeReservation);

}