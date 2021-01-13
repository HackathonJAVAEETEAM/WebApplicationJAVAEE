package be.helha.aemt.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.poi.ss.usermodel.Row;

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
//	public static Etudiant rowToEtudiant(Row row) {
//		String nom;
//		String matricule;
//		String classe;
//		nom = row.getCell(1).getStringCellValue();
//		matricule = row.getCell(2).getStringCellValue();
//		classe = row.getCell(3).getStringCellValue();
//
//		return new Etudiant(nom,matricule,classe,0,0,"0");
//		
//	}
}
