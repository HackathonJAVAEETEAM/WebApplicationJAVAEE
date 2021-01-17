package be.helha.aemt.util.xlsparser;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;


public class XlsParser {
	private static int STUDENT_FIRST_INDEX = 3;
	private Sheet sheet;
	private int rowLastIndex;
	private String sectionName;
	private List<ParsedEtudiant> etudiants;
	private List<ParsedUE> ue;
	
	public List<ParsedUE> getUe() {
		return ue;
	}


	public XlsParser(Sheet sheet) {
		if(sheet==null)
			throw new NullArgumentException();
		this.sheet = sheet;
		this.sectionName = sheet.getSheetName();
		this.rowLastIndex = GetLastIndex();
		
		this.ue = FetchUE();
		this.etudiants = FetchEtudiants();
	}


	public Sheet getSheet() {
		return sheet;
	}

	public int getRowLastIndex() {
		return rowLastIndex;
	}

	public String getSectionName() {
		return sectionName;
	}

	public List<ParsedEtudiant> getEtudiants() {
		return etudiants;
	}

	private int GetLastIndex() {
		int i = 0;
		while(sheet.getRow(0).getCell(i)==null || !"Crédits Totaux".equals(sheet.getRow(0).getCell(i).getStringCellValue())) {
			i++;
		}
		return i;
	}
	
	private List<ParsedEtudiant> FetchEtudiants() {
		Row row;
		int index = STUDENT_FIRST_INDEX;
		row = sheet.getRow(index++);
		
		List<ParsedEtudiant> l = new ArrayList<ParsedEtudiant>();
		
		while (row!=null && row.getCell(2)!=null) {
			ParsedEtudiant etu = new ParsedEtudiant(row,this.ue,this.rowLastIndex);
			l.add(etu);
			row = sheet.getRow(index++);
		}
		return l;
	}
	
	private List<ParsedUE> FetchUE() {
		List<ParsedUE> list = new ArrayList<ParsedUE>();
		int startIndex = 4;
		int endIndex = 6;
		int index = 7;
		while(index<this.rowLastIndex-2) {
			if(this.sheet.getRow(1).getCell(index)!=null && this.sheet.getRow(1).getCell(index).getStringCellValue().startsWith("U")) {
				endIndex = index-1;
				list.add(new ParsedUE(sheet, startIndex, endIndex));
				startIndex = index;
			}
			index++;
		}
		endIndex = index-1;
		ParsedUE lastParsedUE = new ParsedUE(sheet, startIndex, endIndex);
		list.add(lastParsedUE);
		return list;
	}


	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}


	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}


	public void setEtudiants(List<ParsedEtudiant> etudiants) {
		this.etudiants = etudiants;
	}


	public void setUe(List<ParsedUE> ue) {
		this.ue = ue;
	}
	
}
