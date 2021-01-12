package be.helha.aemt.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import be.helha.aemt.util.xlsparser.ParsedAA;

@Entity
public class ActiviteApprentissage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	private int creditAA;
	
	public ActiviteApprentissage() {
	}

	public ActiviteApprentissage(String nom, int creditAA) {
		this.nom = nom;
		this.creditAA = creditAA;
	}

	public ActiviteApprentissage(ParsedAA p) {
		this(p.getNom(),p.getCredits());
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getCreditAA() {
		return creditAA;
	}

	public void setCreditAA(int creditAA) {
		this.creditAA = creditAA;
	}

	@Override
	public String toString() {
		return "ActiviteApprentissage [nom=" + nom + ", creditAA=" + creditAA + "]";
	}
		
}
