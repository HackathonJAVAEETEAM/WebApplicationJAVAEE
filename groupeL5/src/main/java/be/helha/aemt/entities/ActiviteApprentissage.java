package be.helha.aemt.entities;

import java.io.Serializable;

public class ActiviteApprentissage implements Serializable {
	private String nom;
	private String code;
	private int creditAA;
	
	public ActiviteApprentissage() {
	}

	public ActiviteApprentissage(String nom, String code, int creditAA) {
		this.nom = nom;
		this.code = code;
		this.creditAA = creditAA;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getCreditAA() {
		return creditAA;
	}

	public void setCreditAA(int creditAA) {
		this.creditAA = creditAA;
	}

	@Override
	public String toString() {
		return "ActiviteApprentissage [nom=" + nom + ", code=" + code + ", creditAA=" + creditAA + "]";
	}
		
}
