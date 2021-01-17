package be.helha.aemt.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import be.helha.aemt.util.xlsparser.ParsedEtudiant;
import be.helha.aemt.util.xlsparser.ParsedUE;
import be.helha.aemt.util.xlsparser.XlsParser;

@Entity
public class Section implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nom_section;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private ArrayList<Etudiant> listeEtudiant;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private ArrayList<UniteEnseignement> listeUE;
	
	public Section() {
		
	}
	
	public Section(String nom) {
		super();
		this.nom_section = nom;
		this.listeEtudiant = new ArrayList<Etudiant>();
		this.listeUE = new ArrayList<UniteEnseignement>();
	}
	public Section(XlsParser parser) {
		super();
		this.nom_section = parser.getSectionName();
		this.listeEtudiant = new ArrayList<Etudiant>();
		this.listeUE = new ArrayList<UniteEnseignement>();
		
		for(ParsedUE p: parser.getUe()) {
			this.listeUE.add(new UniteEnseignement(p));
		}
		
		for(ParsedEtudiant p : parser.getEtudiants()) {
			this.listeEtudiant.add(new Etudiant(p,this.listeUE));
		}
	}

	public void setListeEtudiant(ArrayList<Etudiant> listeEtudiant) {
		this.listeEtudiant = listeEtudiant;
	}

	public String getNom() {
		return nom_section;
	}

	public void setNom(String nom) {
		this.nom_section = nom;
	}

	public List<Etudiant> getListeEtudiant() {
		return listeEtudiant;
	}
	
	public boolean addEtudiant(Etudiant e) {
		return listeEtudiant.add(e);
	}

	public List<UniteEnseignement> getListeUE() {
		Collections.sort(listeUE);
		return listeUE;
	}
	
	public boolean addUE(UniteEnseignement ue) {
		return listeUE.add(ue);
	}
	
	public String pickRightName() {
		switch(this.getNom()) {
			case "IG":
				return "Informatique de gestion";
			case "AD":
				return "Assistant(e) de direction";
			case "CT":
				return "Comptabilité";
			default:
				return this.getNom();
		}
	}

	@Override
	public String toString() {
		return "Section [nom=" + nom_section + ", listeEtudiant=" + listeEtudiant + ", listeUE=" + listeUE + "]";
	}

	public List<PropositionUE> getBlocUE(String bloc) {
		ArrayList<PropositionUE> res = new ArrayList<PropositionUE>();
		
		for(UniteEnseignement ue: this.getListeUE()) {
			if(ue.getAnnee().equals(bloc))
				res.add(new PropositionUE(ue));
		}
		
		return res;
	}	

}
