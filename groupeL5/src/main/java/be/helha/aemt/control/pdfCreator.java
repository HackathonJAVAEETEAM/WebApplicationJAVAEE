
package be.helha.aemt.control;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.stream.Stream;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import com.sun.enterprise.util.zip.ZipWriter;

import be.helha.aemt.entities.Etudiant;
import be.helha.aemt.entities.PropositionAA;
import be.helha.aemt.entities.PropositionUE;
import be.helha.aemt.entities.Section;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.text.*;

@Named
@SessionScoped
public class pdfCreator implements Serializable{
	
	
	public void createDoc(Section s) throws DocumentException, IOException, URISyntaxException, BadElementException  {
		
		Document document = new Document();
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		/*ExternalContext zp = FacesContext.getCurrentInstance().getExternalContext();
		zp.setResponseHeader("Content-Type", "application/x-zip");
		zp.setResponseHeader("Content-Disposition", "attachment; filename=\"" +"section "+ s.pickRightName()+ ".zip" + "\"");*/
		ec.setResponseHeader("Content-Type", "application/pdf");
		ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + "section "+s.pickRightName()+ ".pdf" + "\"");
		PdfWriter.getInstance(document, ec.getResponseOutputStream());
		document.open();
		
	   
	    Font ueF = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	    Font credTot = new Font(FontFamily.UNDEFINED, 14, Font.BOLD);
	    Font aaF = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
		
		for(Etudiant etudiant : s.getListeEtudiant())
		{
			/*ec.setResponseHeader("Content-Type", "application/pdf");
			ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + "section "+s.pickRightName()+ ".pdf" + "\"");
			PdfWriter.getInstance(document, ec.getResponseOutputStream());
			document.open();*/
			Chunk glue = new Chunk(new VerticalPositionMark());
			PdfPTable table = new PdfPTable(1);
			Phrase p = new Phrase();
			if(etudiant.isDelibere())
			{
				if(etudiant.getPropPae()!=null)
				{
					p.add(new Chunk("Nom de l'étudiant: "+etudiant.getNom() + "\n"));
					p.add(new Chunk("Matricule: "+etudiant.getMatricule() + "\n\n"));
					p.add(new Chunk("Bachelier en "+s.pickRightName()+ "\n\n"));
					
					for(PropositionUE propUe : etudiant.getPropPae().getListeUE())
					{
						p.add(new Chunk("\nUE: "+propUe.getNom(),ueF));
						p.add(glue);
						p.add(new Chunk(propUe.getTotalCredit()+" crédits\n\n",ueF));
						
						for(PropositionAA propAa : propUe.getListeAA())
						{
							p.add(new Chunk("    AA: "+propAa.getNom(),aaF));
							p.add(glue);
							
							if(!propAa.isDispense())
							{
								p.add(new Chunk(propAa.getCredits()+" crédits\n\n",aaF));
							}
							else
							{
								p.add(new Chunk("dispensé\n\n\n",aaF));
							}
						}
					}
					
					p.add(glue);
					p.add(new Chunk("Crédits totaux: "+etudiant.getPropPae().getCreditTotPropPae() + "\n\n",credTot));
					table.addCell(p);
				    document.add(table);
					document.newPage();
				}
				else
				{
					p.add("PAS DE PAE");
				}
			}
		
		}
		document.close();
		FacesContext.getCurrentInstance().responseComplete();
	}
}
