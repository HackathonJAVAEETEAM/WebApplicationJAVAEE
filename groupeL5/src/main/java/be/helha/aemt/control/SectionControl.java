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

@Named
@ViewScoped
public class SectionControl implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	@Inject
	private GestionSectionEJB gestionSection;
	

	
	public List<Section> doSelectAll(){
		return gestionSection.findAll();
	}
	
	public ArrayList<Etudiant> getIgList()
	{
		if(!doSelectAll().isEmpty())
			return (ArrayList<Etudiant>) doSelectAll().get(0).getListeEtudiant();
		else
			return null;
	}
	
	public ArrayList<Etudiant> getAdList()
	{
		if(!doSelectAll().isEmpty())
			return (ArrayList<Etudiant>) doSelectAll().get(1).getListeEtudiant();
		else
			return null;
	}
	
	public ArrayList<Etudiant> getCtList()
	{
		if(!doSelectAll().isEmpty())
			return (ArrayList<Etudiant>) doSelectAll().get(2).getListeEtudiant();
		else
			return null;
	}	

}
