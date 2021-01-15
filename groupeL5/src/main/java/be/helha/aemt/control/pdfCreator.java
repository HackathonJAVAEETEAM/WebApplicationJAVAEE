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
import com.itextpdf.text.Image;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.itextpdf.text.pdf.PdfWriter;

import be.helha.aemt.entities.Etudiant;
import be.helha.aemt.entities.Section;

import com.itextpdf.text.*;

@Named
@SessionScoped
public class pdfCreator implements Serializable{
	
	
	public void createDoc(Section s) throws DocumentException, IOException, URISyntaxException, BadElementException  {
		
		Document document = new Document();
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.setResponseHeader("Content-Type", "application/pdf");
		ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + "sectiontest" + ".pdf" + "\"");
		
	    PdfWriter.getInstance(document, ec.getResponseOutputStream());
	
		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		
	
		document.add(new Chunk(s.getListeEtudiant().get(0).getMatricule() + "\n"));
		
		document.close();
		
		FacesContext.getCurrentInstance().responseComplete();	
	}
	
	private void addTableHeader(PdfPTable table) {
	    Stream.of("column header 1", "column header 2", "column header 3")
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(2);
	        header.setPhrase(new Phrase(columnTitle));
	        table.addCell(header);
	    });
	}
	private void addRows(PdfPTable table) {
	    table.addCell("row 1, col 1");
	    table.addCell("row 1, col 2");
	    table.addCell("row 1, col 3");
	}
	private void addCustomRows(PdfPTable table) throws URISyntaxException, BadElementException, IOException {
			   

	    PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
	    horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(horizontalAlignCell);

	    PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
	    verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
	    table.addCell(verticalAlignCell);
	}
}