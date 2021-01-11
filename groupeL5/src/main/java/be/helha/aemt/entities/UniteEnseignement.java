package be.helha.aemt.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UniteEnseignement implements Serializable {
	
	private String nom;
	private String code;
	private int totalCredit;
	private List<ActiviteApprentissage> AAList;
	
	public UniteEnseignement() {
	}

	public UniteEnseignement(String nom, String code, int totalCredit, List<ActiviteApprentissage> aAList) {
		this.nom = nom;
		this.code = code;
		this.totalCredit = totalCredit;
		AAList = new ArrayList<ActiviteApprentissage>();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(int totalCredit) {
		this.totalCredit = totalCredit;
	}

	public List<ActiviteApprentissage> getAAList() {
		return AAList;
	}
	
	public boolean addToAAList(ActiviteApprentissage aa) {
		return AAList.add(aa);
	}

	@Override
	public String toString() {
		return "UniteEnseignement [nom=" + nom + ", code=" + code + ", totalCredit=" + totalCredit + ", AAList="
				+ AAList + "]";
	}
}
