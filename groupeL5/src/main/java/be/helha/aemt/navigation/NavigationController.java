package be.helha.aemt.navigation;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class NavigationController implements Serializable{
	
	
	

	public NavigationController() {
		
	}

	
	
	public String goToHome() {
//		return "next.xhtml";
		return "liste.xhtml" + "?faces-redirect=true";
	}
	
	

}
