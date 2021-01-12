package be.helha.aemt.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import be.helha.aemt.util.xlsparser.ParsedAssociationUE;
import be.helha.aemt.util.xlsparser.ParsedEtudiant;

@Entity
public class Etudiant implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom_etudiant;
	private String matricule;
	private String classe;
	private int creditsValides;
	private int creditTot;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private ArrayList<AssociationUE> UE;
	
	public Etudiant() {
		
	}
	
	public Etudiant(String nom, String matricule, String classe, int creditsValides, int creditTot) {
		super();
		this.nom_etudiant = nom;
		this.matricule = matricule;
		this.classe = classe;
		this.creditsValides = creditsValides;
		this.creditTot = creditTot;
		this.UE = new ArrayList<AssociationUE>();
	}
	
	public Etudiant(ParsedEtudiant etud, List<UniteEnseignement> ue) {
		this(etud.getNom(),etud.getMatricule(),etud.getClasse(),etud.getCreditsReussi(),etud.getCreditsTotaux());
		for(ParsedAssociationUE p: etud.getListeUE()) {
			this.UE.add(new AssociationUE(p,ue));
		}
	}
	
	public Integer getId() {
		return id;
	}

	public String getNom() {
		return nom_etudiant;
	}

	public void setNom(String nom) {
		this.nom_etudiant = nom;
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

	public List<AssociationUE> getUE() {
		return UE;
	}

	public boolean addUE(AssociationUE ue) {
		return this.UE.add(ue);
	}

	@Override
	public String toString() {
		return "Etudiant [nom=" + nom_etudiant + ", matricule=" + matricule + ", classe=" + classe + ", creditsValides="
				+ creditsValides + ", creditTot=" + creditTot + "]";
	}
	
}
