package be.helha.aemt.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import be.helha.aemt.util.xlsparser.ParsedAssociationAA;
import be.helha.aemt.util.xlsparser.ParsedAssociationUE;

@Entity
public class AssociationUE implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private UniteEnseignement UE;
	private String points;
	private boolean reussi;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<AssociationAA> AA;
	
	public AssociationUE() {
	}

	public AssociationUE(UniteEnseignement uE, String points, boolean reussi) {
		this.UE = uE;
		this.points = points;
		this.reussi = reussi;
		this.AA = new ArrayList<AssociationAA>();
	}

	public AssociationUE(ParsedAssociationUE pars, List<UniteEnseignement> ue2) {
		this.UE = ue2.stream().filter(ue -> pars.getUe().getNom().equals(ue.getNom())).findAny().orElse(null); //on cherche dans la liste des UE déjà créées 
		this.points = pars.getPoints();
		this.reussi = pars.isReussi();
		this.AA = new ArrayList<AssociationAA>();
		for(ParsedAssociationAA p : pars.getAa()) {
			this.AA.add(new AssociationAA(p,this.UE.getAAList()));
		}
	}

	public UniteEnseignement getUE() {
		return UE;
	}

	public void setUE(UniteEnseignement uE) {
		UE = uE;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public boolean isReussi() {
		return reussi;
	}

	public void setReussi(boolean reussi) {
		this.reussi = reussi;
	}

	public List<AssociationAA> getAA() {
		return AA;
	}
	
	public boolean addToAA(AssociationAA aa) {
		return AA.add(aa);
	}

	@Override
	public String toString() {
		return "AssociationUE [id=" + id + ", UE=" + UE + ", points=" + points + ", reussi=" + reussi + ", AA=" + AA
				+ "]";
	}
	
	

}
