
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



@Named
@SessionScoped
public class pdfCreator implements Serializable{
	
	
	/*public void createDoc(Section s) throws  IOException, URISyntaxException  {
		
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.setResponseHeader("Content-Type", "application/zip");
		ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + "section "+s.pickRightName()+".zip" + "\"");
		
		ZipOutputStream zout = new ZipOutputStream(ec.getResponseOutputStream());
		
		
		for(Etudiant etudiant : s.getListeEtudiant())
		{
			if(etudiant.isDelibere())
			{
		        ZipEntry zip = new ZipEntry(etudiant.getNom()+".pdf");  
		        zout.putNextEntry(zip);
		        
		        try {
		        	etudiant.etudiantToPdf(s);
		        }catch(IOException e) {
		        	e.printStackTrace();
		        }
		        
		        zout.closeEntry();

			}
		}

		
		FacesContext.getCurrentInstance().responseComplete();
		
			
	}*/
	
	public void createDoc(Section s) throws  IOException, URISyntaxException  {
		
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.responseReset();
		ec.setResponseHeader("Content-Type", "application/zip");
		ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + "Section_"+s.pickRightName()+".zip" + "\"");
		
		ZipOutputStream zout = new ZipOutputStream(ec.getResponseOutputStream());
		
		//Initialize PDF writer
        PdfWriter writer = new PdfWriter(zout);
        
		for(Etudiant etudiant : s.getListeEtudiant())
		{
			if(etudiant.isDelibere() && etudiant.getPropPae()!=null)
			{	           
		        try {
		        	ZipEntry zip = new ZipEntry(etudiant.getMatricule()+"_"+etudiant.getNom()+".pdf");  
			        zout.putNextEntry(zip);
			        writer.setCloseStream(false);
		        	etudiant.etudiantToPdf(s, writer);
		        	zout.closeEntry();
		        }catch(IOException e) {
		        	e.printStackTrace();
		        }	        
			}
		}
		
		zout.close();
	    FacesContext.getCurrentInstance().responseComplete();
	}
	
}
