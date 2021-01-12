package be.helha.aemt.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import be.helha.aemt.entities.Section;

@Stateless
public class SectionDAO {
	
	@PersistenceContext(unitName = "groupeA5JTA")
	private EntityManager em;
	
	public List<Section> findAll()
	{
		return em.createQuery("SELECT section FROM Section section").getResultList();
	}	
	
}
