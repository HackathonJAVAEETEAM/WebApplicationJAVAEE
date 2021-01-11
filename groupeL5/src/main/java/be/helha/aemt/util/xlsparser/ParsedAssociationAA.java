package be.helha.aemt.util.xlsparser;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class ParsedAssociationAA {
	private ParsedAA aa;
	private String points;
	public ParsedAssociationAA(Row row, ParsedAA aa) {
		this.aa = aa;
		if(row.getCell(aa.getIndex()).getCellType()==CellType.NUMERIC)
			this.points = row.getCell(aa.getIndex()).getNumericCellValue()+"";
		else
			this.points = row.getCell(aa.getIndex()).getStringCellValue();
	}
	@Override
	public String toString() {
		return this.aa.getNom() + " " +this.points +"/20" ;
	}
}
