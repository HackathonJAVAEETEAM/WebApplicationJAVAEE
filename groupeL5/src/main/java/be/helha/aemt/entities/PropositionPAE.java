package be.helha.aemt.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PropositionPAE {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nomEtudiant;
	private String matriculeEtudiant;
	private String blocEtudiant;
	private String sectionEtudiant;
	private int minCredits;
	private int maxCredits;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<PropositionUE> listeUE;
	
	public PropositionPAE() {
		super();
	}
	
	public PropositionPAE(String nomEtudiant, String matriculeEtudiant, String blocEtudiant, String sectionEtudiant) {
		super();
		this.nomEtudiant = nomEtudiant;
		this.minCredits = 30;
		this.maxCredits = 120;
		this.matriculeEtudiant = matriculeEtudiant;
		this.blocEtudiant = blocEtudiant;
		this.sectionEtudiant = sectionEtudiant;
		this.listeUE = new ArrayList<PropositionUE>();
	}
	
	public static PropositionPAE generatePAE(Etudiant etu, Section section) {
		PropositionPAE res = new PropositionPAE(etu.getNom(), etu.getMatricule(), etu.getClasse(), section.getNom());
		

		//TODO faire l'�norme condition de cr�ation d'un pae 
		
		//
		return res;
	}
	
	
	public String getNomEtudiant() {
		return nomEtudiant;
	}
	public void setNomEtudiant(String nomEtudiant) {
		this.nomEtudiant = nomEtudiant;
	}
	public String getMatriculeEtudiant() {
		return matriculeEtudiant;
	}
	public void setMatriculeEtudiant(String matriculeEtudiant) {
		this.matriculeEtudiant = matriculeEtudiant;
	}
	public String getBlocEtudiant() {
		return blocEtudiant;
	}
	public void setBlocEtudiant(String blocEtudiant) {
		this.blocEtudiant = blocEtudiant;
	}
	public String getSectionEtudiant() {
		return sectionEtudiant;
	}
	public void setSectionEtudiant(String sectionEtudiant) {
		this.sectionEtudiant = sectionEtudiant;
	}
	public List<PropositionUE> getListeUE() {
		return listeUE;
	}
	public void setListeUE(List<PropositionUE> listeUE) {
		this.listeUE = listeUE;
	}

	public int getMinCredits() {
		return minCredits;
	}

	public void setMinCredits(int minCredits) {
		this.minCredits = minCredits;
	}

	public int getMaxCredits() {
		return maxCredits;
	}

	public void setMaxCredits(int maxCredits) {
		this.maxCredits = maxCredits;
	}
	

	
}
