package be.helha.aemt.entities;

public class PropositionAA {
	private String nom;
	private int credits;
	private boolean dispense;
	
	
	public PropositionAA(String nom, int credits, boolean dispense) {
		super();
		this.nom = nom;
		this.credits = credits;
		this.dispense = dispense;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getCredits() {
		return credits;
	}


	public void setCredits(int credits) {
		this.credits = credits;
	}


	public boolean isDispense() {
		return dispense;
	}


	public void setDispense(boolean dispense) {
		this.dispense = dispense;
	}
	
	

}
