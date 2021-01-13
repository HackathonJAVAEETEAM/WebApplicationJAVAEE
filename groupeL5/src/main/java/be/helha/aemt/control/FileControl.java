package be.helha.aemt.control;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.http.Part;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import be.helha.aemt.entities.Etudiant;
import be.helha.aemt.entities.Section;
import be.helha.aemt.util.xlsparser.XlsParser;


@Named
@SessionScoped
public class FileControl implements Serializable {
	
	private Part file;
	private String fileContent;
	
	 
	public void save() {
		String fileName = Paths.get(file.getSubmittedFileName()).getFileName().toString();
		final File filepath = new File(fileName);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("groupeA5");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
	  try {
	    fileContent = new Scanner(file.getInputStream()).useDelimiter("\\A").next();
	    
	    final Workbook workbook = WorkbookFactory.create(filepath);
		Sheet sheet;
		
		Iterator<Sheet> sit = workbook.sheetIterator();
		while(sit.hasNext()) {
			sheet = sit.next();
			XlsParser pars = new XlsParser(sheet);	
			Section s = new Section(pars);	
			s.setListeEtudiant(new ArrayList<Etudiant>(s.getListeEtudiant().subList(0,5)));
			tx.begin();
			em.persist(s);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			System.out.println("testbefore : "+dtf.format(now));
			tx.commit();
			LocalDateTime now2 = LocalDateTime.now();  
			System.out.println("testafter : "+dtf.format(now2));
		}
		
	  }catch (IOException e) {
		  e.printStackTrace();
	  }finally {
			em.close();
			emf.close();	
		}
	}
	 
	public Part getFile() {
	  return file;
	}
	 
	public void setFile(Part file) {
	  this.file = file;
	}
	
	public void validateFile(FacesContext ctx, UIComponent comp, Object value) {
		List<FacesMessage> msgs = new ArrayList<FacesMessage>();
		Part file = (Part)value;
		if (file.getSize() > 1024) {
		msgs.add(new FacesMessage("file too big"));
		}
		if (!"text/plain".equals(file.getContentType())) {
		msgs.add(new FacesMessage("not a text file"));
		}
		if (!msgs.isEmpty()) {
		throw new ValidatorException(msgs);
		}
	}

}
