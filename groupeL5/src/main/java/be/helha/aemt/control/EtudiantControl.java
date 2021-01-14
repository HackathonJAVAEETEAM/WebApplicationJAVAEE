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

@Named
@SessionScoped
public class EtudiantControl implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Etudiant etudiant;
	
	@Inject
	private GestionEtudiantEJB gestionEtudiant;
	
	public EtudiantControl() {
		etudiant = new Etudiant();
	}
	
	public List<Etudiant> doSelectAll(){
		return gestionEtudiant.findAll();
		
	}
	
	public String doGetDetails(Etudiant u) {
		etudiant = u;
		return "details.xhtml";
	}
	
	
	public List<Etudiant> doSelectWithParam(String classe){
		if(classe.isEmpty())
			return doSelectAll();
		else
			return gestionEtudiant.findWithParam(classe);
	}
	
	 public Etudiant getEtudiant() {
	        return etudiant;
	}
	
	    
    public void setEtudiant(Etudiant v) {
    	etudiant = v;
    }
    
    public void persist() {
    	gestionEtudiant.updateUeEtudiant(etudiant);
    }
    
    public String getTypeOfDeliberation() {
    	if(etudiant.isDelibere())
    		return "doneDelib";
    	else
    		return "toDelib";
    }
    
    public void changeStatus() {
    	etudiant.switchDelib();
    	gestionEtudiant.updateDelibEtudiant(etudiant);
    }
    
	
}
