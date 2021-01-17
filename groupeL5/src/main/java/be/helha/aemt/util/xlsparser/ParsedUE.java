package be.helha.aemt.util.xlsparser;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

public class ParsedUE {
	private String nom;
	
	public String getNom() {
		return nom;
	}
	
	private String annee;
	
	private int credits;
	
	public String getAnnee() {
		return annee;
	}
	
	public void setAnnee(String annee) {
		this.annee = annee;
	}
	
	public int getCredits() {
		return credits;
	}
	
	public void setCredits(int credits) {
		this.credits = credits;
	}
	
	public int getColEndIndex() {
		return colEndIndex;
	}
	
	public void setColEndIndex(int colEndIndex) {
		this.colEndIndex = colEndIndex;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setColStartIndex(int colStartIndex) {
		this.colStartIndex = colStartIndex;
	}
	
	public void setListAA(List<ParsedAA> listAA) {
		this.listAA = listAA;
	}
	
	private int colStartIndex;
	
	public List<ParsedAA> getListAA() {
		return listAA;
	}
	
	private int colEndIndex;
	
	private List<ParsedAA> listAA;
	
	public ParsedUE(Sheet sheet, int colStartIndex, int colEndIndex) {
		this.colEndIndex = colEndIndex;
		this.colStartIndex = colStartIndex;
		this.nom = sheet.getRow(0).getCell(colStartIndex).getStringCellValue();
		this.annee = sheet.getRow(1).getCell(colStartIndex).getStringCellValue().split(" ")[1];
		this.credits = (int) sheet.getRow(2).getCell(colStartIndex).getNumericCellValue();
		this.listAA = FetchAA(sheet,colStartIndex,colEndIndex);
	}
	
	private static List<ParsedAA> FetchAA(Sheet sheet, int colStartIndex, int colEndIndex) {
		int index = 2;
		List<ParsedAA> list = new ArrayList<ParsedAA>();
		while((colStartIndex+index)<=colEndIndex) {
			list.add(new ParsedAA(sheet, colStartIndex+index));
			index++;
		}
		return list;
	}
	
	@Override
	public String toString() {
		return "ParsedUE [nom=" + nom + ", annee=" + annee + ", credits=" + credits + ", listAA=" + listAA + "]";
	}
	public int getColStartIndex() {
		return colStartIndex;
	}
}
