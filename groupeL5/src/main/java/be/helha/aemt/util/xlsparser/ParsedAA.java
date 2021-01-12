package be.helha.aemt.util.xlsparser;

import org.apache.poi.ss.usermodel.Sheet;

public class ParsedAA {
	private String nom;
	private int credits;
	private int index;
	public ParsedAA(Sheet sheet, int index) {
		this.nom = sheet.getRow(0).getCell(index).getStringCellValue();
		this.credits = -1;
		if(sheet.getRow(2).getCell(index)!=null)
			this.credits = (int) sheet.getRow(2).getCell(index).getNumericCellValue(); //On a modifié l'EpIn en AD pour faire fonctionner ?
		this.index = index;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getNom() {
		return nom;
	}
	public int getCredits() {
		return credits;
	}
	public int getIndex() {
		return index;
	}
	@Override
	public String toString() {
		return "ParsedAA [nom=" + nom + ", credits=" + credits + "]";
	}
}
