package be.helha.aemt.control;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.http.Part;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import be.helha.aemt.ejb.GestionFileEJB;
import be.helha.aemt.entities.Etudiant;
import be.helha.aemt.entities.Section;
import be.helha.aemt.util.xlsparser.XlsParser;

@Named
@ViewScoped
public class FileControl implements Serializable {
	
	private static final long serialVersionUID = -8933603853735051193L;
	
	@Inject
	private GestionFileEJB gestionFile;

	private Part file;

	public void save() {

	    String fileName = Paths.get(file.getSubmittedFileName()).getFileName().toString();
	    System.out.println(fileName);

	    try (InputStream input = file.getInputStream()) {
	    	
	    	gestionFile.cleanTableaBeforeImport();
	    	
	    	File tempXlsFile = File.createTempFile("temp", ".xls");
	        Files.write(tempXlsFile.toPath(), input.readAllBytes());
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("groupeA5");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			
			
			try {
				final Workbook workbook = WorkbookFactory.create(tempXlsFile);
				Sheet sheet;				
				Iterator<Sheet> sit = workbook.sheetIterator();
				while(sit.hasNext()) {
					sheet = sit.next();
					XlsParser pars = new XlsParser(sheet);	
					Section s = new Section(pars);
					tx.begin();
					em.persist(s);
					tx.commit();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
				em.close();
				emf.close();	
			}
	    }
	    catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	 
	public Part getFile() {
	  return file;
	}
	 
	public void setFile(Part file) {
	  this.file = file;
	}

}
