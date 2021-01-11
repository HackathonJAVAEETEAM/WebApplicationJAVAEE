package be.helha.aemt.util.xlsparser;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import be.helha.aemt.entities.Etudiant;

public class ParsedEtudiant {

	private List<ParsedAssociationUE> listeUE;
	private String nom;
	private String matricule;
	private String classe;
	private int creditsReussi;
	private int creditsTotaux;
	public ParsedEtudiant(Row row, List<ParsedUE> ue, int rowLastIndex) {

		nom = row.getCell(1).getStringCellValue();
		matricule = row.getCell(2).getStringCellValue();
		classe = row.getCell(3).getStringCellValue();
		creditsReussi = -1;
		if(row.getCell(rowLastIndex-1)!=null)
			creditsReussi = (int) row.getCell(rowLastIndex-1).getNumericCellValue();
		creditsTotaux = (int) row.getCell(rowLastIndex).getNumericCellValue();
		listeUE = FetchAssociationUE(ue, row);

	}
	
	private static List<ParsedAssociationUE> FetchAssociationUE(List<ParsedUE> ue, Row row) {
		List<ParsedAssociationUE> list = new ArrayList<ParsedAssociationUE>();
		for(ParsedUE p : ue) {
			if(row.getCell(p.getColStartIndex()).getCellType()!=CellType.NUMERIC) {
				if(!row.getCell(p.getColStartIndex()).getStringCellValue().equals("-")) {
					list.add(new ParsedAssociationUE(row, p));
				}
			} else {
				list.add(new ParsedAssociationUE(row,p));
			}
		}
		return list;
	}
	
	
	@Override
	public String toString() {
		String str = "UE's:\n";
		for(ParsedAssociationUE p:this.listeUE) {
			str = str+"\t" + p + "\n";
		}
		return "Nom:" + this.nom + "\n" + str;
	}

}
