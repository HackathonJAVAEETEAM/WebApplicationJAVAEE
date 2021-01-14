package be.helha.aemt.util;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
				s.setListeEtudiant(new ArrayList<Etudiant>(s.getListeEtudiant().subList(0,1)));
				
				tx.begin();
				em.persist(s);
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now();  
				System.out.println("testbefore : "+dtf.format(now));
				tx.commit();
				LocalDateTime now2 = LocalDateTime.now();  
				System.out.println("testafter : "+dtf.format(now2));
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			em.close();
			emf.close();	
		}
	}
}
