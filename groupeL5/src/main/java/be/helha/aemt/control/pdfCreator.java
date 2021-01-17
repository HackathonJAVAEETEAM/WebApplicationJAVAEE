
package be.helha.aemt.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;

import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.inject.Named;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import be.helha.aemt.entities.Etudiant;
import be.helha.aemt.entities.PropositionAA;
import be.helha.aemt.entities.PropositionUE;
import be.helha.aemt.entities.Section;

/*
 * Cette classe va nous permettre de récupérer un fichier zip
 * contenant les pdf des PAE des étudiants qui ont obtenu le statut délibéré
 * Une méthode export depuis la classe étudiant a spécialement été conçue dans cette optique
 */

@Named
@SessionScoped
public class pdfCreator implements Serializable{
	
	public void createDoc(Section s) throws  IOException, URISyntaxException  {
		
		//Je prépare l'interaction entre le côté client et le côté via les requetes http
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.responseReset();
		ec.setResponseHeader("Content-Type", "application/zip");
		ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + "Section_"+s.pickRightName()+".zip" + "\"");
		
		//Je crée un OutputStream "ZIP" pour y stocker mes pdf
		ZipOutputStream zout = new ZipOutputStream(ec.getResponseOutputStream());
		
		//Initialize PDF writer
        PdfWriter writer = new PdfWriter(zout);
        
        //Je boucle sur les étdiants de la section
		for(Etudiant etudiant : s.getListeEtudiant())
		{
			//Je vérifie si l'étudiant remplit les conditions pour crée son PAE
			if(etudiant.isDelibere() && etudiant.getPropPae()!=null)
			{	           
		        try {
		        	//Dans le zip je crée une entrée contenant un pdf
		        	ZipEntry zip = new ZipEntry(etudiant.getMatricule()+"_"+etudiant.getNom()+".pdf");  
		        	//Je préviens l'output stream que je vais remplir son zip
			        zout.putNextEntry(zip);
			        
			        //Ici je demande à l'objet Writer de ne pas se fermer pour pouvoir boucler sur tous mes pdfs pour chaque étudiant
			        writer.setCloseStream(false);
			        //Je crée les documents des étudiants
		        	etudiant.etudiantToPdf(s, writer);
		        	//Je ferme la création d'un fichier pdf dans le zip pour passer au  suivant
		        	zout.closeEntry();
		        }catch(IOException e) {
		        	e.printStackTrace();
		        }	        
			}
		}
		
		//Je ferme l'output stream
		zout.close();
		
		//Je préviens le serveur que le côté client ets ok et qu'il reprend la main
	    FacesContext.getCurrentInstance().responseComplete();
	}
	
}
