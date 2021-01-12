package be.helha.aemt.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import be.helha.aemt.entities.ActiviteApprentissage;
import be.helha.aemt.entities.Etudiant;
import be.helha.aemt.entities.UniteEnseignement;

public class MainData {

	public static void main(String[] args) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("groupeA5");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			
			//Etudiant e1 = new Etudiant("Dieu Alexis", "LA188756", "2A", 54, 60);
			UniteEnseignement ue1 = new UniteEnseignement("Gestion","code", 15);
			ActiviteApprentissage aa = new ActiviteApprentissage("Comptabilité", 15);
			ue1.addToAAList(aa);
			
			tx.begin();
			em.persist(ue1);
			tx.commit();
			
			em.close();
			emf.close();
	}

}
