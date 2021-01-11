package be.helha.aemt.util;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import be.helha.aemt.ejb.IGestionEtudiantRemote;
import be.helha.aemt.entities.Etudiant;

public class MainJNDI {

	public static void main(String[] args) {
		try {
			Context context = new InitialContext();
			IGestionEtudiantRemote beanEtudiant = (IGestionEtudiantRemote) context.lookup("java:global/groupeA5/GestionEtudiantEJB!be.helha.aemt.ejb.IGestionEtudiantRemote");
			Etudiant e1 = new Etudiant("Leveau Nathan", "LA188731", "3A", 54, 60);
			
			//beanEtudiant.add(e1);
			System.out.println(beanEtudiant.findAll());

		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
}
