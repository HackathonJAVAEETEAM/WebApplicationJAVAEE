package be.helha.aemt.control;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class NavigationControl implements Serializable {
	
	public NavigationControl(){
		
	}
	
	public String doLogin() 
	{
		return "liste" + "?faces-redirect=true";
	}
}
