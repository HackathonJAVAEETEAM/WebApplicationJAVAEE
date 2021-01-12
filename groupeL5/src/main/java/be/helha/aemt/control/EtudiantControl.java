package be.helha.aemt.control;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import be.helha.aemt.ejb.GestionEtudiantEJB;
import be.helha.aemt.entities.Etudiant;


@Named
@SessionScoped
public class EtudiantControl implements Serializable {
	
private Etudiant etudiant;
	
	@Inject
	private GestionEtudiantEJB gestionEtudiant;
	
	public EtudiantControl() {
		etudiant = new Etudiant();
	}
	
	public List<Etudiant> doSelectAll(){
		return gestionEtudiant.findAll();
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
	
}
