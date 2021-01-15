package be.helha.aemt.entities;

import java.util.ArrayList;
import java.util.List;

public class PropositionPAE {
	private String nomEtudiant;
	private String matriculeEtudiant;
	private String blocEtudiant;
	private String sectionEtudiant;
	private List<PropositionUE> listeUE;
	
	public PropositionPAE(String nomEtudiant, String matriculeEtudiant, String blocEtudiant, String sectionEtudiant) {
		super();
		this.nomEtudiant = nomEtudiant;
		this.matriculeEtudiant = matriculeEtudiant;
		this.blocEtudiant = blocEtudiant;
		this.sectionEtudiant = sectionEtudiant;
		this.listeUE = new ArrayList<PropositionUE>();
	}
	
	public static PropositionPAE generatePAE(Etudiant etu, Section section) {
		PropositionPAE res = new PropositionPAE(etu.getNom(), etu.getMatricule(), etu.getClasse(), section.getNom());
		//ICI GENERER TOUT L'OBJET PAE EN FONCTION DES RESULTATS
		//Là ça devient la barbamerde
		//TODO faire l'énorme condition de création d'un pae 
		
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
	

	
}
