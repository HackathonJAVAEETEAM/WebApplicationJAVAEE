package be.helha.aemt.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import be.helha.aemt.util.xlsparser.ParsedAA;
import be.helha.aemt.util.xlsparser.ParsedUE;

@Entity
public class UniteEnseignement implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	private String annee;
	private int totalCredit;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<ActiviteApprentissage> AAList;
	
	public UniteEnseignement() {
	}


	public UniteEnseignement(String nom, String annee, int totalCredit) {
		this.nom = nom;
		this.annee = annee;
		this.totalCredit = totalCredit;
		AAList = new ArrayList<ActiviteApprentissage>();
	}

	public UniteEnseignement(ParsedUE ue) {
		this(ue.getNom(),ue.getAnnee(),ue.getCredits());
		for(ParsedAA p : ue.getListAA()) {
			this.AAList.add(new ActiviteApprentissage(p));
		}
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public int getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(int totalCredit) {
		this.totalCredit = totalCredit;
	}

	public List<ActiviteApprentissage> getAAList() {
		return AAList;
	}
	
	public boolean addToAAList(ActiviteApprentissage aa) {
		return AAList.add(aa);
	}

	@Override
	public String toString() {
		return "UniteEnseignement [nom=" + nom + ", annee=" + annee + ", totalCredit=" + totalCredit + ", AAList="
				+ AAList + "]";
	}
}
