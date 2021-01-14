package be.helha.aemt.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import be.helha.aemt.util.xlsparser.ParsedAssociationAA;

@Entity
public class AssociationAA implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private ActiviteApprentissage AA;
	private String points;
	private boolean reussi;
	
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
		this.reussi = false;
		if(NumberUtils.isCreatable(this.points)) {
			if(Double.parseDouble(this.points)>=10) {
				this.reussi = true;
			}
		}
	}

	public ActiviteApprentissage getAA() {
		return AA;
	}

	public void setAA(ActiviteApprentissage aA) {
		AA = aA;
	}

	public String getPoints() {
		if(NumberUtils.isCreatable(points)) {
			return points+"/20";
		}
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "AssociationAA [id=" + id + ", AA=" + AA + ", points=" + points + "]";
	}

	public boolean isReussi() {
		return reussi;
	}

	public void setReussi(boolean reussi) {
		this.reussi = reussi;
	}

	public Integer getId() {
		return id;
	}	

}
