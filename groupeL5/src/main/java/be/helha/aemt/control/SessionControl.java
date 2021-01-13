package be.helha.aemt.control;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@SessionScoped
public class SessionControl implements Serializable {

	public SessionControl(){

	}

	public String doLogin()
	{
		return "liste.xhtml" + "?faces-redirect=true";
	}

	public String logOut() {
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
		return "liste.xhtml" + "?faces-redirect=true";
	}
}
