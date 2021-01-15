package be.helha.aemt.entities;

import java.util.ArrayList;
import java.util.List;


public class PropositionUE {
	private String nom;
	private String bloc;
	private int totalCredit;
	private boolean dispense;
	private List<PropositionAA> listeAA;

	public PropositionUE(String nom, String bloc, int totalCredit, boolean dispense) {
		super();
		this.nom = nom;
		this.bloc = bloc;
		this.totalCredit = totalCredit;
		this.dispense = dispense;
		this.listeAA = new ArrayList<PropositionAA>();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getBloc() {
		return bloc;
	}

	public void setBloc(String bloc) {
		this.bloc = bloc;
	}

	public int getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(int totalCredit) {
		this.totalCredit = totalCredit;
	}

	public boolean isDispense() {
		return dispense;
	}

	public void setDispense(boolean dispense) {
		this.dispense = dispense;
	}

	public List<PropositionAA> getListeAA() {
		return listeAA;
	}

	public void setListeAA(List<PropositionAA> listeAA) {
		this.listeAA = listeAA;
	}
	
	
}
