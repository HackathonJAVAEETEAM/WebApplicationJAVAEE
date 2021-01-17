package be.helha.aemt.navigation;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import be.helha.aemt.control.EtudiantControl;
import be.helha.aemt.entities.Etudiant;

/*
 * SessionScoped permet d'obteni des données partagées par une seule session côté client
 */
@Named
@SessionScoped
public class NavigationController implements Serializable {

	public NavigationController() {
		
	}
	
		
	public String goToHome() {
		return "liste.xhtml" + "?faces-redirect=true";
	}
	
	public String goToAboutUs() {
		return "aboutus.xhtml" + "?faces-redirect=true";
	}
	
	
}
