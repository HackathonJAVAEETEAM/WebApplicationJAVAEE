package be.helha.aemt.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import be.helha.aemt.ejb.GestionSectionEJB;
import be.helha.aemt.entities.Etudiant;
import be.helha.aemt.entities.Section;
/*
 * La sectionControl me permet de gérer l'interaction entre les objets sections qui ont été persisté
 * sur la base de données
 */
@Named
@ViewScoped
public class SectionControl implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private GestionSectionEJB gestionSection;
	
	//Contacter l'EJB pour obtenir la liste des sections
	public List<Section> doSelectAll(){
		return gestionSection.findAll();
	}
	
	//Je récupère la fenêtre active sur la page
	public String getActive(int index) {
		if(index==0)
			return "active";
		else
			return "";
	}			

}
