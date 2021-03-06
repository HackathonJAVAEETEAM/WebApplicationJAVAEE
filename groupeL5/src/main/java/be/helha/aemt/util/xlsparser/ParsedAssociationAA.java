package be.helha.aemt.util.xlsparser;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class ParsedAssociationAA {
	
	private ParsedAA aa;
	private String points;
	
	/*
	 * Constructeur d'une association depuis activit� apprentissage
	 */
	public ParsedAssociationAA(Row row, ParsedAA aa) {
		this.aa = aa;
		if(row.getCell(aa.getIndex()).getCellType()==CellType.NUMERIC)
			this.points = row.getCell(aa.getIndex()).getNumericCellValue()+"";
		else
			this.points = row.getCell(aa.getIndex()).getStringCellValue();
	}
	
	public ParsedAA getAa() {
		return aa;
	}
	
	public void setAa(ParsedAA aa) {
		this.aa = aa;
	}
	
	public String getPoints() {
		return points;
	}
	
	public void setPoints(String points) {
		this.points = points;
	}
	
	@Override
	public String toString() {
		return this.aa.getNom() + " " +this.points +"/20" ;
	}
}
