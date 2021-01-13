package be.helha.aemt.navigation;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import be.helha.aemt.control.EtudiantControl;
import be.helha.aemt.entities.Etudiant;

@Named
@SessionScoped
public class NavigationController implements Serializable {
	
	//public EtudiantControl etudiantC;
	

	public NavigationController() {
		
	}
	/*public String etudiantPrecedent() {
		int etudpre = getIdEtudiantPrecedent();
		
		return"details.xhtml" +"?faces-redirect=true";
	}
	public int getIdEtudiantPrecedent() {
		return etudiantC.getEtudiant().getId() > 0 ? etudiantC.getEtudiant().getId() - 1: 0;
	}
	public String etudiantSuivant() {
		return"details.xhtml" +"?faces-redirect=true";
	}*/
		
	public String goToHome() {
		return "liste.xhtml" + "?faces-redirect=true";
	}
	
	
}
