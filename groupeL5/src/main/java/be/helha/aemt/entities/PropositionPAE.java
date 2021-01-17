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
 * Je cr�e une entit� pour mon model afin de pouvoir le persister
 */
@Entity
public class PropositionPAE {
	/*
	 * Je g�n�re un Id pour mon entit�
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nomEtudiant;
	private String matriculeEtudiant;
	private String blocEtudiant;
	private String sectionEtudiant;
	private int minCredits;
	private int maxCredits;
	
	/*
	 * Association OneToMany car une seule propositionPAE par etudiant 
	 * pour plusieurs propositions de UE
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<PropositionUE> listeUE;
	
	public PropositionPAE() {
		
	}
	
	public PropositionPAE(String nomEtudiant, String matriculeEtudiant, String blocEtudiant, String sectionEtudiant) {
		super();
		this.nomEtudiant = nomEtudiant;
		this.minCredits = 30;
		this.maxCredits = 75;
		this.matriculeEtudiant = matriculeEtudiant;
		this.blocEtudiant = blocEtudiant;
		this.sectionEtudiant = sectionEtudiant;
		this.listeUE = new ArrayList<PropositionUE>();
	}
	/*
	 * Constructeur AssociationUE pour construire un objet qui a �t� r�cup�rer depuis le parser
	 */
	public static PropositionPAE generatePAE(Etudiant etu, Section section) {
		PropositionPAE res = new PropositionPAE(etu.getNom(), etu.getMatricule(), etu.getClasse(), section.getNom());
		int credB1 = 0, credB2 = 0, credB3 = 0;
		int credAcquisB1 = 0, credAcquisB2 = 0, credAcquisB3 = 0;
		/*
		 * par d�faut on add toutes les ue rat�es au prochain pae, on en profite pour calculer son nombre de cr�dits r�ussi par bloc pour la suite
		 */
		for(AssociationUE ue: etu.getUE()) {
			if(!ue.isReussi()) {
				PropositionUE newUe = new PropositionUE(ue);
				res.addUE(newUe);
			} else {
				if(ue.getUE().getAnnee().equals("1B")) {
					credAcquisB1+=ue.getUE().getTotalCredit();
				} else if(ue.getUE().getAnnee().equals("2B")) {
					credAcquisB2+=ue.getUE().getTotalCredit();
				} else if(ue.getUE().getAnnee().equals("3B")) {
					credAcquisB3+=ue.getUE().getTotalCredit();
				} //un else if pour terminer, on ne sait jamais que les ann�es soient mal encod�es, du coup autant ajouter � la main si erreur que g�n�rer un mauvais pae
			}
			if(ue.getUE().getAnnee().equals("1B")) {
				credB1+=ue.getUE().getTotalCredit();
			} else if(ue.getUE().getAnnee().equals("2B")) {
				credB2+=ue.getUE().getTotalCredit();
			} else if(ue.getUE().getAnnee().equals("3B")) {
				credB3+=ue.getUE().getTotalCredit();
			}
		}
		/*
		 * Premiers cas, etudiants de bloc 1 avec 60 cr�dits de bloc 1 dans le pae
		 */
		if(credB1==60) {
			if(credAcquisB1<30) {
				//On ne fait rien, cours d�j� ajout�s, l'�tudiant est r�inscrit en B1
				res.blocEtudiant = "1B";
			} else if(credAcquisB1>=30 && credAcquisB1<45) {
				//On ne fait rien, cours d�j� ajout�s, l'�tudiant est r�inscrit en B1 et ne peut avoir que 60 cred max
				res.blocEtudiant = "1B";
				res.maxCredits = 60;
			} else if(credAcquisB1==60) {
				//L'etudiant a acc�s � tous les cours du B2 et est ajout� en B2
				res.blocEtudiant = "2B";
				res.listeUE.addAll(section.getBlocUE("2B"));
			} else {// cas des 45>59
				res.blocEtudiant = "2B";
				res.listeUE.addAll(section.getBlocUE("2B"));
				res.minCredits = 55;
			}
		}
		/*
		 * Deuxi�me cas, �tudiant de bloc 2 avec 60 cr�dits de bloc 2 dans le pae et 60 cr�dits r�ussis de bloc 2
		 */
		else if(credB2==60) {
			if(credAcquisB2==60) {
				res.blocEtudiant = "3B";
				res.listeUE.addAll(section.getBlocUE("3B"));
			}
		}
		/*
		 * Trop d'info sont manquantes pour pouvoir supposer la suite de la g�n�ration pour les blocs 2 et 3.
		 * Nous ne pouvons pas savoir si les �tudiants seront diplomables �tant donn� que nous n'avons pas d'historique des pae pour les ann�es pr�c�dentes. 
		 */

		//TODO
		
		//
		return res;
	}
	
	
	public boolean addUE(PropositionUE propositionUE) {		
		return this.listeUE.add(propositionUE);
	}

	public String getNomEtudiant() {
		return nomEtudiant;
	}
	public void setNomEtudiant(String nomEtudiant) {
		this.nomEtudiant = nomEtudiant;
	}
	public String getMatriculeEtudiant() {
		return matriculeEtudiant;
	}
	public void setMatriculeEtudiant(String matriculeEtudiant) {
		this.matriculeEtudiant = matriculeEtudiant;
	}
	public String getBlocEtudiant() {
		return blocEtudiant;
	}
	public void setBlocEtudiant(String blocEtudiant) {
		this.blocEtudiant = blocEtudiant;
	}
	public String getSectionEtudiant() {
		return sectionEtudiant;
	}
	public void setSectionEtudiant(String sectionEtudiant) {
		this.sectionEtudiant = sectionEtudiant;
	}
	public List<PropositionUE> getListeUE() {
		return listeUE;
	}
	public void setListeUE(List<PropositionUE> listeUE) {
		this.listeUE = listeUE;
	}

	public int getMinCredits() {
		return minCredits;
	}

	public void setMinCredits(int minCredits) {
		this.minCredits = minCredits;
	}

	public int getMaxCredits() {
		return maxCredits;
	}

	public void setMaxCredits(int maxCredits) {
		this.maxCredits = maxCredits;
	}
	
	public int getCreditTotPropPae() {
		int tot = 0;
		
		for(PropositionUE propUe : listeUE)
		{
			tot+= propUe.getTotalCredit();
		}
		
		return tot;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PropositionPAE [id=" + id + ", nomEtudiant=" + nomEtudiant + ", matriculeEtudiant=" + matriculeEtudiant
				+ ", blocEtudiant=" + blocEtudiant + ", sectionEtudiant=" + sectionEtudiant + ", minCredits="
				+ minCredits + ", maxCredits=" + maxCredits + ", listeUE=" + listeUE + "]";
	}
	
	
	

}
