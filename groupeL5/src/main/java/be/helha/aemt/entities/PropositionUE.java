package be.helha.aemt.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
/*
 * Je crée une entité pour mon model afin de pouvoir le persister
 */
@Entity
public class PropositionUE {
	/*
	 * Je génère un Id pour mon entité
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nom;
	private String bloc;
	private int totalCredit;
	private boolean dispense;
	/*
	 * Association OneToMany car une seule propositionUE
	 * pour plusieurs propositions de AA
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<PropositionAA> listeAA;
	
	public PropositionUE() {
		super();
	}

	public PropositionUE(String nom, String bloc, int totalCredit, boolean dispense) {
		super();
		this.nom = nom;
		this.bloc = bloc;
		this.totalCredit = totalCredit;
		this.dispense = dispense;
		this.listeAA = new ArrayList<PropositionAA>();
	}
	/*
	 * Constructeur pour adapter une UniteEnsignement en une propositionUE
	 */
	public PropositionUE(UniteEnseignement ue) {
		this(ue.getNom(),ue.getAnnee(),ue.getTotalCredit(), false);
		ArrayList<PropositionAA> aas = new ArrayList<PropositionAA>();
		for (ActiviteApprentissage aa: ue.getAAList()) {
			aas.add(new PropositionAA(aa.getNom(),aa.getCreditAA(),false));
		}
		this.listeAA = aas;
	}
	
	/*
	 * Constructeur pour adapter une assocationUE en une propositionUE
	 */
	public PropositionUE(AssociationUE ue) {
		this(ue.getUE().getNom(),ue.getUE().getAnnee(),ue.getUE().getTotalCredit(), ue.isReussi());
		for (AssociationAA aa: ue.getAA()) {
			this.listeAA.add(new PropositionAA(aa.getAA().getNom(),aa.getAA().getCreditAA(),aa.isReussi()));
		}
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

	/*
	 * Méthode equals pour utiliser le contains et remove des ARRAYLIST
	 */
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof PropositionUE)
		{
			return this.getNom().equals(((PropositionUE) obj).getNom());
		}
		return false;
	}
	
	
	
	
}
