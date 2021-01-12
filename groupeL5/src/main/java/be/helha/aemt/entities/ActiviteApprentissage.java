package be.helha.aemt.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ActiviteApprentissage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
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
