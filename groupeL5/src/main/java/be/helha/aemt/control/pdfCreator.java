
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
 * Cette classe va nous permettre de r�cup�rer un fichier zip
 * contenant les pdf des PAE des �tudiants qui ont obtenu le statut d�lib�r�
 * Une m�thode export depuis la classe �tudiant a sp�cialement �t� con�ue dans cette optique
 */

@Named
@SessionScoped
public class pdfCreator implements Serializable{
	
	public void createDoc(Section s) throws  IOException, URISyntaxException  {
		
		//Je pr�pare l'interaction entre le c�t� client et le c�t� via les requetes http
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.responseReset();
		ec.setResponseHeader("Content-Type", "application/zip");
		ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + "Section_"+s.pickRightName()+".zip" + "\"");
		
		//Je cr�e un OutputStream "ZIP" pour y stocker mes pdf
		ZipOutputStream zout = new ZipOutputStream(ec.getResponseOutputStream());
		
		//Initialize PDF writer
        PdfWriter writer = new PdfWriter(zout);
        
        //Je boucle sur les �tdiants de la section
		for(Etudiant etudiant : s.getListeEtudiant())
		{
			//Je v�rifie si l'�tudiant remplit les conditions pour cr�e son PAE
			if(etudiant.isDelibere() && etudiant.getPropPae()!=null)
			{	           
		        try {
		        	//Dans le zip je cr�e une entr�e contenant un pdf
		        	ZipEntry zip = new ZipEntry(etudiant.getMatricule()+"_"+etudiant.getNom()+".pdf");  
		        	//Je pr�viens l'output stream que je vais remplir son zip
			        zout.putNextEntry(zip);
			        
			        //Ici je demande � l'objet Writer de ne pas se fermer pour pouvoir boucler sur tous mes pdfs pour chaque �tudiant
			        writer.setCloseStream(false);
			        //Je cr�e les documents des �tudiants
		        	etudiant.etudiantToPdf(s, writer);
		        	//Je ferme la cr�ation d'un fichier pdf dans le zip pour passer au  suivant
		        	zout.closeEntry();
		        }catch(IOException e) {
		        	e.printStackTrace();
		        }	        
			}
		}
		
		//Je ferme l'output stream
		zout.close();
		
		//Je pr�viens le serveur que le c�t� client ets ok et qu'il reprend la main
	    FacesContext.getCurrentInstance().responseComplete();
	}
	
}
