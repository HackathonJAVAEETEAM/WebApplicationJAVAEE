package be.helha.aemt.control;

import java.io.Serializable;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import be.helha.aemt.ejb.GestionSectionEJB;
import be.helha.aemt.entities.Etudiant;
import be.helha.aemt.entities.Section;

@ManagedBean
@Named
@SessionScoped
public class SectionControl implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Section sectionIG;
	private Section sectionAD;
	private Section sectionCT;
	
	@Inject
	private GestionSectionEJB gestionSection;
	
	public SectionControl() {
		sectionIG = new Section();
		sectionAD = new Section();
		sectionCT = new Section();
	}
	
	@PostConstruct
	public void init() {
		sectionIG = doSelectAll().get(0);
		sectionAD = doSelectAll().get(1);
		sectionCT = doSelectAll().get(2);
	}
	
	public List<Section> doSelectAll(){
		return gestionSection.findAll();
	}
	
	public List<Etudiant> getIgList()
	{
		return sectionIG.getListeEtudiant();
	}
	
	public List<Etudiant> getAdList()
	{
		return sectionAD.getListeEtudiant();
	}
	
	public List<Etudiant> getCtList()
	{
		return sectionCT.getListeEtudiant();
	}	

}
