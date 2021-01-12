package be.helha.aemt.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import be.helha.aemt.util.xlsparser.ParsedEtudiant;
import be.helha.aemt.util.xlsparser.XlsParser;

@Entity
public class Section implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String nom;
	private List<Etudiant> listeEtudiant;
	private List<UniteEnseignement> listeUE;
	
	public Section() {
		
	}
	
	public Section(String nom) {
		super();
		this.nom = nom;
		this.listeEtudiant = new ArrayList<Etudiant>();
		this.listeUE = new ArrayList<UniteEnseignement>();
	}
	public Section(XlsParser parser) {
		super();
		this.nom = parser.getSectionName();
		this.listeEtudiant = new ArrayList<Etudiant>();
		
		//remplir la liste des UE
		for(ParsedEtudiant p : parser.getEtudiants()) {
			this.listeEtudiant.add(new Etudiant(p,this.listeUE));
		}
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Etudiant> getListeEtudiant() {
		return listeEtudiant;
	}
	
	public boolean addEtudiant(Etudiant e) {
		return listeEtudiant.add(e);
	}

	public List<UniteEnseignement> getListeUE() {
		return listeUE;
	}
	
	public boolean addUE(UniteEnseignement ue) {
		return listeUE.add(ue);
	}

	@Override
	public String toString() {
		return "Section [nom=" + nom + ", listeEtudiant=" + listeEtudiant + ", listeUE=" + listeUE + "]";
	}	

}
