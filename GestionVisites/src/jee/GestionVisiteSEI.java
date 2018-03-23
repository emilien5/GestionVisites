package jee;

import java.util.ArrayList;

import javax.jws.WebService;

@WebService(name = "GestionVisiteSEI", targetNamespace = "http://jee/")
public interface GestionVisiteSEI {

	ArrayList<Visite> trouverVisite(Visite uneVisite);

	int reserverVisite(ReservationVisite uneReservation);

	String payerVisite(int codeReservation);

	boolean annulerVisite(int codeReservation);

}