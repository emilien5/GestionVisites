package jee;

public class Visite {
	
	private int idVisite;
	private String typeVisite;
	private String ville;
	private String dateVisite;
	private String prixVisite;
	
//	public Visite(int idVisites, String typeVisites, String villes, String prixVisites) {
//		setIdVisite(idVisites);
//		if(typeVisites == "none") {
//			setTypeVisite("0");
//		}
//		if(villes == "none") {
//			setVille("0");
//		}
//		if(prixVisites == "none") {
//			setPrixVisite("0");
//		} else {
//		setTypeVisite(typeVisites);
//		setVille(villes);
//		setPrixVisite(prixVisites);
//		}
//	}
//	
//	public Visite(String typeVisites, String villes, String prixVisites) {
//		setIdVisite(0);
//		if(typeVisites == "none") {
//			setTypeVisite("0");
//		}
//		if(villes == "none") {
//			setVille("0");
//		}
//		if(prixVisites == "none") {
//			setPrixVisite("0");
//		} else {
//		setTypeVisite(typeVisites);
//		setVille(villes);
//		setPrixVisite(prixVisites);
//		}
//	}
//	
//	public Visite(String villes, String prixVisites) {
//		setIdVisite(0);
//		setTypeVisite("0");
//		if(villes == "none") {
//			setVille("0");
//		}
//		if(prixVisites == "none") {
//			setPrixVisite("0");
//		} else {
//		setVille(villes);
//		setPrixVisite(prixVisites);
//		}
//	}
//	
//	public Visite(String prixVisites) {
//		setIdVisite(0);
//		setTypeVisite("0");
//		setVille("0");
//		if(prixVisites == "none") {
//			setPrixVisite("0");
//		} else {
//		setPrixVisite(prixVisites);
//		}
//	}
//	
//	public Visite() {
//		setIdVisite(0);
//		setTypeVisite("0");
//		setVille("0");
//		setPrixVisite("0");
//	}
	
	public int getIdVisite() {
		return idVisite;
	}
	public void setIdVisite(int idVisite) {
		this.idVisite = idVisite;
	}
	public String getTypeVisite() {
		return typeVisite;
	}
	public void setTypeVisite(String typeVisite) {
		this.typeVisite = typeVisite;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getDateVisite() {
		return dateVisite;
	}
	public void setDateVisite(String dateVisite) {
		this.dateVisite = dateVisite;
	}
	public String getPrixVisite() {
		return prixVisite;
	}
	public void setPrixVisite(String prixVisite) {
		this.prixVisite = prixVisite;
	}
	
	
	

}
