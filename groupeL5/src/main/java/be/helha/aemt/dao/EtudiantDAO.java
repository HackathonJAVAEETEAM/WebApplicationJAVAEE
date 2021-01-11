package be.helha.aemt.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import be.helha.aemt.entities.Etudiant;

@Stateless
public class EtudiantDAO {
	
	@PersistenceContext(unitName = "groupeA5JTA")
	private EntityManager em;
	
	public List<Etudiant> findAll()
	{
		return em.createQuery("SELECT etudiant FROM Etudiant etudiant").getResultList();
	}
	
	public Etudiant add(Etudiant etudiant) {
		em.persist(etudiant);
		return etudiant;
	}
	
}
