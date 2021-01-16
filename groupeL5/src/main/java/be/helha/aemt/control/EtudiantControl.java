package be.helha.aemt.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import be.helha.aemt.ejb.GestionEtudiantEJB;
import be.helha.aemt.entities.Etudiant;
import be.helha.aemt.entities.PropositionUE;
import be.helha.aemt.entities.Section;
import be.helha.aemt.entities.UniteEnseignement;

@Named
@SessionScoped
public class EtudiantControl implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Etudiant etudiant;
	private Section section;
	List<SelectablePropUe> listSelect = new ArrayList<SelectablePropUe>();
	
	@Inject
	private GestionEtudiantEJB gestionEtudiant;
	
	public EtudiantControl() {
		etudiant = new Etudiant();
		section = new Section();
	}
	
	public List<Etudiant> doSelectAll(){
		return gestionEtudiant.findAll();
		
	}
	
	public String doGetDetails(Section s,Etudiant e) {
		section = s;
		etudiant = e;
		return "details.xhtml";
	}
	
	 public Etudiant getEtudiant() {
	        return etudiant;
	}
	    
    public void setEtudiant(Etudiant v) {
    	etudiant = v;
    }
    
    public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public void persist() {
    	gestionEtudiant.updateUeEtudiant(etudiant);
    }
    
    public void changeStatus() {
    	etudiant.switchDelib();
    	gestionEtudiant.updateDelibEtudiant(etudiant);
    }
    
    public boolean isPaeNotEmpty() {
    	return etudiant.getPropPae() != null;
    }
    
    public void generateAutoPae() {
    	etudiant.generateEtudiantPae(section);
    	etudiant.getPropPae().setId(gestionEtudiant.generatePropPae(etudiant));
    	System.out.println("GENERATE ID PROPPAE "+etudiant.getPropPae().getId());
    }
    
    public List<SelectablePropUe> getListUeToPick() {  
    	
    	ArrayList<PropositionUE> arrPropUe = new ArrayList<PropositionUE>();
    	listSelect.clear();
    	
    	for(UniteEnseignement ue : section.getListeUE())
    	{
    		arrPropUe.add(new PropositionUE(ue));
    	}
    	
    	if(etudiant.getPropPae()!=null)
    	{
	    	for(PropositionUE pUe : etudiant.getPropPae().getListeUE())
	    	{
	    		arrPropUe.remove(pUe);
	    	}

	    	for(PropositionUE ppue : arrPropUe)
	    	{
	    		listSelect.add(new SelectablePropUe(ppue));
	    	}
    	}
    	
    	return listSelect; 
    }
    
    public class SelectablePropUe{
    	private PropositionUE propUe;
    	private boolean selected;
    	
    	public SelectablePropUe(PropositionUE propUe){
    		this.propUe = propUe;
    		this.selected = false;
    	}

		public PropositionUE getPropUe() {
			return propUe;
		}

		public void setPropUe(PropositionUE propUe) {
			this.propUe = propUe;
		}
		
		public boolean isSelected() {
			return selected;
		}

		public void setSelected(boolean selected) {
			this.selected = selected;
		}

		public String toString() {
			return propUe.getNom() +" "+ selected;
		}
    }
    
    public void addPropPae() {
    	for(SelectablePropUe selectPropUe : listSelect)
    	{
    		if(selectPropUe.isSelected())
    			etudiant.getPropPae().addUE(selectPropUe.getPropUe());
    	}
    	gestionEtudiant.addPropPae(etudiant);
    }
    
    public void removePropUe(PropositionUE propUe)
    {
    	etudiant.getPropPae().getListeUE().remove(propUe);
    	gestionEtudiant.removePropUe(etudiant);
    }
    
	
}
