package be.helha.aemt.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import be.helha.aemt.entities.Etudiant;

@Stateless
public class EtudiantDAO {
	
	@PersistenceContext(unitName = "groupeA5JTA")
	private EntityManager em;
	
	public Etudiant add(Etudiant etudiant) {
		em.persist(etudiant);
		return null;
	}

}
