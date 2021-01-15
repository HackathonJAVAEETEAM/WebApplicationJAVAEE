package be.helha.aemt.control;

import java.io.Serializable;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import be.helha.aemt.ejb.GestionEtudiantEJB;
import be.helha.aemt.entities.AssociationAA;
import be.helha.aemt.entities.AssociationUE;
import be.helha.aemt.entities.Etudiant;
import be.helha.aemt.entities.Section;

@Named
@SessionScoped
public class EtudiantControl implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Etudiant etudiant;
	private Section section;
	
	@Inject
	private GestionEtudiantEJB gestionEtudiant;
	
	public EtudiantControl() {
		etudiant = new Etudiant();
		section = new Section();
	}
	
	public List<Etudiant> doSelectAll(){
		return gestionEtudiant.findAll();
		
	}
	
	public String doGetDetails(Section s,Etudiant e) {
		section = s;
		etudiant = e;
		return "details.xhtml";
	}
	
	 public Etudiant getEtudiant() {
	        return etudiant;
	}
	    
    public void setEtudiant(Etudiant v) {
    	etudiant = v;
    }
    
    public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public void persist() {
    	gestionEtudiant.updateUeEtudiant(etudiant);
    }
    
    public void changeStatus() {
    	etudiant.switchDelib();
    	gestionEtudiant.updateDelibEtudiant(etudiant);
    }
    
	
}
