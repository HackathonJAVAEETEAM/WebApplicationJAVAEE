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
		for(AssociationUE ue: etu.getUE()) {
			if(!ue.isReussi()) {
				PropositionUE newUe = new PropositionUE(ue);
				res.addUE(newUe);
			}
		}
		

		//TODO faire l'énorme condition de création d'un pae 
		
		//
		return res;
	}
	
	
	public boolean addUE(PropositionUE propositionUE) {		
		return this.listeUE.add(propositionUE);
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
	
	public int getCreditTotPropPae() {
		int tot = 0;
		
		for(PropositionUE propUe : listeUE)
		{
			tot+= propUe.getTotalCredit();
		}
		
		return tot;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PropositionPAE [id=" + id + ", nomEtudiant=" + nomEtudiant + ", matriculeEtudiant=" + matriculeEtudiant
				+ ", blocEtudiant=" + blocEtudiant + ", sectionEtudiant=" + sectionEtudiant + ", minCredits="
				+ minCredits + ", maxCredits=" + maxCredits + ", listeUE=" + listeUE + "]";
	}
	
	
	

}
