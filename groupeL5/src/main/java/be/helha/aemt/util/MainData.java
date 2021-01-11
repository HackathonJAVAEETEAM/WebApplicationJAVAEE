package be.helha.aemt.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import be.helha.aemt.entities.Etudiant;

public class MainData {

	public static void main(String[] args) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("groupeA5");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			
			Etudiant e1 = new Etudiant("Compas Jordan", "LA188735", "3A", 54, 60);
			
			tx.begin();
			em.persist(e1);
			tx.commit();
			
			em.close();
			emf.close();
	}

}
