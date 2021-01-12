package be.helha.aemt.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AssociationUE implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private UniteEnseignement UE;
	private String points;
	private boolean reussi;
	private List<AssociationAA> AA;
	
	public AssociationUE() {
	}

	public AssociationUE(UniteEnseignement uE, String points, boolean reussi) {
		this.UE = uE;
		this.points = points;
		this.reussi = reussi;
		this.AA = new ArrayList<AssociationAA>();
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
