package be.helha.aemt.control;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import be.helha.aemt.ejb.GestionSectionEJB;
import be.helha.aemt.entities.Etudiant;
import be.helha.aemt.entities.Section;

@Named
@SessionScoped
public class SectionControl implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Section section;
	
	@Inject
	private GestionSectionEJB gestionSection;
	
	public SectionControl() {
		section = new Section();	
	}
	
	public List<Section> doSelectAll(){
		return gestionSection.findAll();
	}
	
	public List<Etudiant> getIgList()
	{
		return doSelectAll().get(0).getListeEtudiant();
	}
	
	public List<Etudiant> getAdList()
	{
		return doSelectAll().get(1).getListeEtudiant();
	}
	
	public List<Etudiant> getCtList()
	{
		return doSelectAll().get(2).getListeEtudiant();
	}	

}
