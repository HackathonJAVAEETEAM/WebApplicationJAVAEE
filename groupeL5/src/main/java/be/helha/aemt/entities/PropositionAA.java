package be.helha.aemt.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/*
 * Je crée une entité pour mon model afin de pouvoir le persister
 */
@Entity
public class PropositionAA {
	/*
	 * Je génère un Id pour mon entité
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nom;
	private int credits;
	private boolean dispense;
	
	
	public PropositionAA() {
		super();
	}

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
