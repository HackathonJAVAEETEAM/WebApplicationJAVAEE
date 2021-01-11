package be.helha.aemt.util.xlsparser;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class ParsedAssociationUE {
	private ParsedUE ue;
	private String points;
	private boolean reussi;
	private List<ParsedAssociationAA> aa;
	public ParsedAssociationUE(Row row, ParsedUE ue) {
		int index = ue.getColStartIndex();
		this.ue = ue;
		if(row.getCell(index).getCellType()==CellType.NUMERIC)
			this.points = row.getCell(index).getNumericCellValue()+"";
		else
			this.points = row.getCell(index).getStringCellValue();
		this.reussi = row.getCell(index+1).getStringCellValue().equals("O");
		this.aa = FetchAA(row, ue);
	}
	
	@Override
	public String toString() {
		String str = this.ue.getNom()+"("+this.points+"/20):\n";
		for(ParsedAssociationAA p:this.aa) {
			str = str+"\t\t" + p + "\n";
		}
		return str;
	}

	private static List<ParsedAssociationAA> FetchAA(Row row, ParsedUE ue) {
		List<ParsedAssociationAA> list = new ArrayList<ParsedAssociationAA>();
		for(ParsedAA p : ue.getListAA()) {
			if(row.getCell(p.getIndex())!=null) {
				list.add(new ParsedAssociationAA(row, p));
			}
		}
		return list;
	}
}