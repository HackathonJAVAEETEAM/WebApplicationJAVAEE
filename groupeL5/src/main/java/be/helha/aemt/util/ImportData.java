package be.helha.aemt.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import be.helha.aemt.entities.Etudiant;
import be.helha.aemt.entities.Section;
import be.helha.aemt.util.xlsparser.XlsParser;


public class ImportData {
	public static void main(String[] args) {
		String fileName = "src/main/resources/files/listes.xlsx";
		final File file = new File(fileName);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("groupeA5");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		
		try {
			final Workbook workbook = WorkbookFactory.create(file);
			Sheet sheet;
			
			Iterator<Sheet> sit = workbook.sheetIterator();
			while(sit.hasNext()) {
				sheet = sit.next();
				XlsParser pars = new XlsParser(sheet);	
				Section s = new Section(pars);	
				tx.begin();
				em.persist(s);
				System.out.println("testbefore");
				tx.commit();
				System.out.println("testAfter");
				break;
			}

		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			em.close();
			emf.close();	
		}
	}
}
