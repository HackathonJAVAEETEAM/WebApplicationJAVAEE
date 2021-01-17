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
	
	/*
	 * Constructeur d'une ParsedAssociationUE depuis une ligne excel
	 */
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

	public ParsedUE getUe() {
		return ue;
	}

	public void setUe(ParsedUE ue) {
		this.ue = ue;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public boolean isReussi() {
		return reussi;
	}

	public void setReussi(boolean reussi) {
		this.reussi = reussi;
	}

	public List<ParsedAssociationAA> getAa() {
		return aa;
	}

	public void setAa(List<ParsedAssociationAA> aa) {
		this.aa = aa;
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
	
	@Override
	public String toString() {
		String str = this.ue.getNom()+"("+this.points+"/20):\n";
		for(ParsedAssociationAA p:this.aa) {
			str = str+"\t\t" + p + "\n";
		}
		return str;
	}
}
