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
			
			//Etudiant e1 = new Etudiant("Dieu Alexis", "LA188324", "3A", 56, 60);
			//Etudiant e2 = new Etudiant("Leveau Nathan", "LA187965", "3A", 12, 60);
			//Etudiant e3 = new Etudiant("Lopez Lucas", "LA188754", "2A", 44, 60);
			UniteEnseignement ue1 = new UniteEnseignement("Gestion","code", 15);
			ActiviteApprentissage aa = new ActiviteApprentissage("Comptabilité", 15);
			ue1.addToAAList(aa);
			
			tx.begin();
			//em.persist(e1);
			//em.persist(e2);
			//em.persist(e3);
			em.persist(ue1);
			tx.commit();
			
			em.close();
			emf.close();
	}

}
