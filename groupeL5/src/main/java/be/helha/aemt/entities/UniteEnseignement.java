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
/*
 * Je crée une entité pour mon model afin de pouvoir le persister
 */
@Entity
public class UniteEnseignement implements Serializable, Comparable {
	
	private static final long serialVersionUID = 1L;
	/*
	 * Je génère un Id pour mon entité
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	private String annee;
	private int totalCredit;
	/*
	 * Association OneToMany car une seule UniteEnsiegnement
	 * pour plusieurs Activité d'apprentissage
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private ArrayList<ActiviteApprentissage> AAList;
	
	public UniteEnseignement() {
	}


	public UniteEnseignement(String nom, String annee, int totalCredit) {
		this.nom = nom;
		this.annee = annee;
		this.totalCredit = totalCredit;
		AAList = new ArrayList<ActiviteApprentissage>();
	}

	/*
	 * Constructeur d'une unité d'enseignement depuis le parser
	 */
	public UniteEnseignement(ParsedUE ue) {
		this(ue.getNom(),ue.getAnnee(),ue.getCredits());
		for(ParsedAA p : ue.getListAA()) {
			this.AAList.add(new ActiviteApprentissage(p));
		}
	}

	/*
	 * Getters & Setters
	 */
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

	/*
	 * Compare To afin de pouvoir tirer mes listes d'objet
	 * Utilisée essentiellement dans l'affichage des listes de Unité enseignement 
	 */
	@Override
	public int compareTo(Object o) {
		return this.getNom().compareTo(((UniteEnseignement)o).getNom());
	}
}
