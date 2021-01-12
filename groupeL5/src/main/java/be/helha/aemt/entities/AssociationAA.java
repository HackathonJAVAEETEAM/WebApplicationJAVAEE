package be.helha.aemt.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import be.helha.aemt.util.xlsparser.ParsedAssociationAA;

@Entity
public class AssociationAA implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private ActiviteApprentissage AA;
	private String points;	
	
	public AssociationAA() {
	}

	public AssociationAA(ActiviteApprentissage aA, String points) {
		super();
		AA = aA;
		this.points = points;
	}

	public AssociationAA(ParsedAssociationAA pars, List<ActiviteApprentissage> aa) {
		this.AA= aa.stream().filter(aca -> pars.getAa().getNom().equals(aca.getNom())).findAny().orElse(null);
		this.points = pars.getPoints();
	}

	public ActiviteApprentissage getAA() {
		return AA;
	}

	public void setAA(ActiviteApprentissage aA) {
		AA = aA;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "AssociationAA [id=" + id + ", AA=" + AA + ", points=" + points + "]";
	}		

}
