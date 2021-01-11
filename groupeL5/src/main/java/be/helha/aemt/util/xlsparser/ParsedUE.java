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
