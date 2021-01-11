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
	private GestionEtudiantEJB bean;
	
	
	public List<Etudiant> doSelectAll(){
		return bean.findAll();
	}
	public String doGetDetails(Etudiant u) {
		etudiant = u;
		return "index.xhtml?faces-redirect=true";
	}
	 public Etudiant getEtudiant() {
	        return etudiant;
	}
	    
    public void setEtudiant(Etudiant v) {
    	etudiant = v;
    }
	public String doAdd() {
		System.out.println(etudiant);
		bean.add(etudiant);
		return "index.xhtml?faces-redirect=true";
	}
	

}
