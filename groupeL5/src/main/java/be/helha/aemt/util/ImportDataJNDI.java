package be.helha.aemt.util;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import be.helha.aemt.ejb.IGestionEtudiantRemote;
import be.helha.aemt.entities.Etudiant;
import be.helha.aemt.entities.Section;
import be.helha.aemt.util.xlsparser.XlsParser;

public class ImportDataJNDI {
	public static void main(String[] args) {
		String fileName = "src/main/resources/files/listes.xlsx";
		final File file = new File(fileName);

		
		//beanEtudiant.add(e1);
		
		try {
			final Workbook workbook = WorkbookFactory.create(file);
			Sheet sheet;
			
			Context context = new InitialContext();
			IGestionEtudiantRemote beanEtudiant = (IGestionEtudiantRemote) context.lookup("java:global/groupeA5/GestionEtudiantEJB!be.helha.aemt.ejb.IGestionEtudiantRemote");
			
			Iterator<Sheet> sit = workbook.sheetIterator();
			while(sit.hasNext()) {
				sheet = sit.next();
				XlsParser pars = new XlsParser(sheet);	
				Section s = new Section(pars);	
				int index = 1;
				for(Etudiant e : s.getListeEtudiant())
				{
					beanEtudiant.add(e);
					System.out.println("Chargement: "+ index+"/"+ s.getListeEtudiant().size());
					index++;
				}
			}

		}catch(IOException | NamingException e) {
			e.printStackTrace();
		}
	}
}
