package be.helha.aemt.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FileDAO {
	
	@PersistenceContext(unitName = "groupeA5JTA")
	private EntityManager em;
	
	private final String deleteQuery = "DELETE FROM Section section";
	
	public void cleanTableBeforeImport()
	{
		em.createQuery(deleteQuery).executeUpdate();
	}
}
