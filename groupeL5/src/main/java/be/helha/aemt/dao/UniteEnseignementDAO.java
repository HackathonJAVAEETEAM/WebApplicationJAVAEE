package be.helha.aemt.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import be.helha.aemt.entities.Etudiant;
import be.helha.aemt.entities.UniteEnseignement;

@Stateless
public class UniteEnseignementDAO {
	
	@PersistenceContext(unitName = "groupeA5JTA")
	private EntityManager em;
	
	public List<UniteEnseignement> findAll()
	{
		return em.createQuery("SELECT uniteEnseignement FROM UniteEnseignement uniteEnseignement").getResultList();
	}
	
	public UniteEnseignement add(UniteEnseignement ue) {
		em.persist(ue);
		return ue;
	}
}
