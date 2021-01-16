package be.helha.aemt.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import com.itextpdf.text.log.SysoCounter;

import be.helha.aemt.entities.AssociationAA;
import be.helha.aemt.entities.AssociationUE;
import be.helha.aemt.entities.Etudiant;
import be.helha.aemt.entities.PropositionPAE;

@Stateless
public class EtudiantDAO {
	
	@PersistenceContext(unitName = "groupeA5JTA")
	private EntityManager em;
	
	public List<Etudiant> findAll()
	{
		return em.createQuery("SELECT etudiant FROM Etudiant etudiant").getResultList();
	}
	
	public List<Etudiant> findWithParam(String classe)
	{
		return em.createQuery("SELECT etudiant FROM Etudiant etudiant WHERE etudiant.classe='"+classe+"'").getResultList();
	}
	
	public Etudiant add(Etudiant etudiant) {
		em.persist(etudiant);
		return etudiant;
	}
	
	public void updateUeEtudiant(Etudiant etudiant) {
		Etudiant varEtudiant = em.find(Etudiant.class, etudiant.getId());
		varEtudiant.setUE((ArrayList<AssociationUE>) etudiant.getUE());
		em.merge(varEtudiant);
	}
	
	public void updateDelibEtudiant(Etudiant etudiant) {
		Etudiant varEtudiant = em.find(Etudiant.class, etudiant.getId());
		varEtudiant.setDelibere(etudiant.isDelibere());
		em.merge(varEtudiant);
	}

	public Integer generatePropPae(Etudiant etudiant) {
		Etudiant varEtudiant = em.find(Etudiant.class, etudiant.getId());
		varEtudiant.setPropPae(etudiant.getPropPae());
		em.merge(varEtudiant);
		
		
		
		varEtudiant = em.find(Etudiant.class, etudiant.getId());
		System.out.println("GENERATE DAO PROP PAE "+varEtudiant.getPropPae());
		return varEtudiant.getPropPae().getId();
	}

	public void removePropUe(Etudiant etudiant) {
		Etudiant varEtudiant = em.find(Etudiant.class, etudiant.getId());
		varEtudiant.setPropPae(etudiant.getPropPae());
		em.merge(varEtudiant);
	}

	public void addPropPae(Etudiant etudiant) {
		PropositionPAE varProp = em.find(PropositionPAE.class, etudiant.getPropPae().getId());
		varProp.setListeUE(etudiant.getPropPae().getListeUE());
		em.merge(varProp);
	}
	
	

}
