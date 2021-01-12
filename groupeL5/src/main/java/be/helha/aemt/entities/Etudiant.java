package be.helha.aemt.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Etudiant implements Serializable{


	private static final long serialVersionUID = 6960303104532622578L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	private String matricule;
	private String classe;
	private int creditsValides;
	private int creditTot;
	//private List UE;
	
	public Etudiant() {
		
	}
	
	public Etudiant(String nom, String matricule, String classe, int creditsValides, int creditTot) {
		this.nom = nom;
		this.matricule = matricule;
		this.classe = classe;
		this.creditsValides = creditsValides;
		this.creditTot = creditTot;
	}
	
	public Integer getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public int getCreditsValides() {
		return creditsValides;
	}

	public void setCreditsValides(int creditsValides) {
		this.creditsValides = creditsValides;
	}

	public int getCreditTot() {
		return creditTot;
	}

	public void setCreditTot(int creditTot) {
		this.creditTot = creditTot;
	}

	@Override
	public String toString() {
		return "Etudiant [nom=" + nom + ", matricule=" + matricule + ", classe=" + classe + ", creditsValides="
				+ creditsValides + ", creditTot=" + creditTot + "]";
	}
	
}
