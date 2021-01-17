package be.helha.aemt.control;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import be.helha.aemt.entities.Section;
import be.helha.aemt.util.xlsparser.XlsParser;
/*
 * La classe FileControl est utilisée dans le cadre de la gestion
 * des échanges de fichier, notamment pour l'import de données au format Excel.
 */
@Named
@ViewScoped
public class FileControl implements Serializable {
	
	private static final long serialVersionUID = -8933603853735051193L;
	
	@Inject
	private GestionFileEJB gestionFile;

	private Part file;

	public void save() {

		//Création du chemin
	    String fileName = Paths.get(file.getSubmittedFileName()).getFileName().toString();

	    try (InputStream input = file.getInputStream()) {
	    	
	    	//Je clean les tables de la base de données
	    	gestionFile.cleanTableaBeforeImport();
	    	
	    	//Je crée un fichier temporaire pour le serveur
	    	File tempXlsFile = File.createTempFile("temp", ".xls");
	    	
	    	//J'ecris dans ce fichier temporaire ce que le client a entré.
	        Files.write(tempXlsFile.toPath(), input.readAllBytes());
	        
	        //Je lance l'entity factory
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("groupeA5");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			
			
			try {
				//Grâce au fichier temporaire le serveur peut lire les données.
				//Je crée une work book importée de la librairie pooi
				final Workbook workbook = WorkbookFactory.create(tempXlsFile);
				//Je crée ma feuille
				Sheet sheet;
				//Je crée un iterator capable de lire les feuilles qui représentent les sections
				Iterator<Sheet> sit = workbook.sheetIterator();
				
				//Je boucle sur les feuilles
				while(sit.hasNext()) {
					sheet = sit.next();
					
					//Je transforme la feuille au format de l'objet Section
					XlsParser pars = new XlsParser(sheet);	
					
					//Je crée l'objet section
					Section s = new Section(pars);
					
					//Je débute la transaction
					tx.begin();
					
					//Je persiste mon objet section
					em.persist(s);
					
					//Je commit sur la bd
					tx.commit();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
				//Je ferme mes ressources
				em.close();
				emf.close();	
			}
	    }
	    catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	 
	/*
	 * Getters & Setters
	 */
	
	public Part getFile() {
	  return file;
	}
	 
	public void setFile(Part file) {
	  this.file = file;
	}

}
