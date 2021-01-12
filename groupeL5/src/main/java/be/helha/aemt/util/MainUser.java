package be.helha.aemt.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import be.helha.aemt.entities.ActiviteApprentissage;
import be.helha.aemt.entities.AssociationAA;
import be.helha.aemt.entities.AssociationUE;
import be.helha.aemt.entities.Etudiant;
import be.helha.aemt.entities.Section;
import be.helha.aemt.entities.UniteEnseignement;
import be.helha.aemt.entities.Utilisateur;

public class MainUser {
	public static void main(String[] args) {
		
		/*EntityManagerFactory emf = Persistence.createEntityManagerFactory("groupeA5");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Utilisateur u1 = new Utilisateur("u", "C/6TXnDDIcfKOvx1zg0MovmLVCLgCLsxwAxtfx8cCtY=","ADMIN");
		
		tx.begin();
		em.persist(u1);
		tx.commit();
		
		em.close();
		emf.close();*/
		
		try {
			System.out.println(digestSomething("u"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static String digestSomething(String clearText) throws NoSuchAlgorithmException {
		return new String(Base64.getEncoder().encode(MessageDigest.getInstance("SHA-256").digest(clearText.getBytes(StandardCharsets.UTF_8))));
	}
}
