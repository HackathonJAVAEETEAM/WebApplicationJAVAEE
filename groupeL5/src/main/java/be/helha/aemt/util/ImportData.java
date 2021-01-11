package be.helha.aemt.util;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


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
			}
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
