package be.helha.aemt.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import be.helha.aemt.entities.AssociationAA;
import be.helha.aemt.entities.AssociationUE;
import be.helha.aemt.entities.Etudiant;

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
		//Etudiant varEtudiant = em.find(Etudiant.class, etudiant);
		//varEtudiant.setUE((ArrayList<AssociationUE>) etudiant.getUE());
		System.out.println(etudiant);
		//System.out.println(varEtudiant);
		//em.persist(varEtudiant);
		em.persist(etudiant);
		
	}

}
