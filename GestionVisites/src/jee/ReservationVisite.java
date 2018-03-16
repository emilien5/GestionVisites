package jee;

public class ReservationVisite {
	
	private int idReservation;
	private int nombrePlace;
	private boolean paiementEffectue;
	
	public int getIdReservation() {
		return idReservation;
	}
	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}
	public int getNombrePlace() {
		return nombrePlace;
	}
	public void setNombrePlace(int nombrePlace) {
		this.nombrePlace = nombrePlace;
	}
	public boolean isPaiementEffectue() {
		return paiementEffectue;
	}
	public void setPaiementEffectue(boolean paiementEffectue) {
		this.paiementEffectue = paiementEffectue;
	}
	
	
}
