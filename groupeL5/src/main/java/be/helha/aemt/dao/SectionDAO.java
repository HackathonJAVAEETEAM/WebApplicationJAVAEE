package be.helha.aemt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

import be.helha.aemt.entities.Section;
/*
 * Je rends ma classe stateless pour communiquer avec les clients
 */
@Stateless
public class SectionDAO {
	
	@PersistenceContext(unitName = "groupeA5JTA")
	private EntityManager em;
	
	public List<Section> findAll()
	{
		return em.createQuery("SELECT section FROM Section section").getResultList();
	}	
	
}
