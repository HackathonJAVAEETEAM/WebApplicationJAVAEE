package be.helha.aemt.util;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import be.helha.aemt.entities.Section;
import be.helha.aemt.util.xlsparser.XlsParser;


public class ImportData {
	public static void main(String[] args) {
		String fileName = "src/main/resources/files/listes.xlsx";
		final File file = new File(fileName);
		
		try {
			final Workbook workbook = WorkbookFactory.create(file);
			Sheet sheet;
			
			Iterator<Sheet> sit = workbook.sheetIterator();
			while(sit.hasNext()) {
				sheet = sit.next();
				XlsParser pars = new XlsParser(sheet);	
				Section s = new Section(pars);
				System.out.println(s.getListeUE());
				System.out.println("SECTION "+s.getNom());
				System.out.println("UE "+s.getListeUE().size());
				System.out.println("ETU "+s.getListeEtudiant().size());
				System.out.println(s.getListeEtudiant().get(0).getUE().get(0).getAA().get(0));
			}						
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
