package be.helha.aemt.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.apache.commons.lang3.math.NumberUtils;

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
	private String moyenne;
	private int creditsValides;
	private int creditTot;
	private boolean delibere;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private ArrayList<AssociationUE> UE;
	
	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private PropositionPAE propPae;
	
	public Etudiant() {
		
	}
	
	public Etudiant(String nom, String matricule, String classe, int creditsValides, int creditTot, String moyenne) {
		super();
		this.nom_etudiant = nom;
		this.matricule = matricule;
		this.classe = classe;
		this.creditsValides = creditsValides;
		this.creditTot = creditTot;
		this.setMoyenne(moyenne);
		this.setDelibere(false);
		this.UE = new ArrayList<AssociationUE>();
		this.delibere = false;
		this.propPae = null;
	}
	
	public Etudiant(ParsedEtudiant etud, List<UniteEnseignement> ue) {
		this(etud.getNom(),etud.getMatricule(),etud.getClasse(),etud.getCreditsReussi(),etud.getCreditsTotaux(), etud.getMoyenne());
		for(ParsedAssociationUE p: etud.getListeUE()) {
			this.UE.add(new AssociationUE(p,ue));
		}
	}
	
	public void generateEtudiantPae(Section section) {
		this.propPae = PropositionPAE.generatePAE(this, section);
	}
	
	public PropositionPAE getPropPae() {
		return propPae;
	}

	public void setPropPae(PropositionPAE propPae) {
		this.propPae = propPae;
	}

	public void setUE(ArrayList<AssociationUE> uE) {
		UE = uE;
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
		int res = 0;
		for(AssociationUE aue: this.UE) {
			if(aue.isReussi()) {
				res+=aue.getUE().getTotalCredit();
			}
		}
		return res;
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
		Collections.sort(UE);
		return UE;
	}

	public boolean addUE(AssociationUE ue) {
		return this.UE.add(ue);
	}

	public boolean isDelibere() {
		return delibere;
	}
	
	public String isDelibereStringFormat() {
		if(isDelibere())
			return "Fait";
		else
			return "A faire";
	}

	public void setDelibere(boolean delibere) {
		this.delibere = delibere;
	}

	public String getMoyenne() {
		if(NumberUtils.isCreatable(moyenne)) {
			return Math.round((NumberUtils.createDouble(moyenne)*100.0)*100.0)/100.0+"%";
		}
		return moyenne;
	}
		
	public void switchDelib() {
		this.delibere = !delibere;
	}
	
	public void setMoyenne(String moyenne) {
		this.moyenne = moyenne;
	}
	
	@Override
	public String toString() {
		return "Etudiant [nom=" + nom_etudiant + ", matricule=" + matricule + ", classe=" + classe + ", creditsValides="
				+ creditsValides + ", creditTot=" + creditTot + "]";
	}

	
}
