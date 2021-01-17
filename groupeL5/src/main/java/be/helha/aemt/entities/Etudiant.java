package be.helha.aemt.entities;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import org.apache.commons.lang3.math.NumberUtils;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TabAlignment;

import be.helha.aemt.util.xlsparser.ParsedAssociationUE;
import be.helha.aemt.util.xlsparser.ParsedEtudiant;

@Entity
public class Etudiant implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom_etudiant;
	private String matricule;
	private String classe;
	private String moyenne;
	private int creditsValides;
	private int creditTot;
	private boolean delibere;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private ArrayList<AssociationUE> UE;
	
	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private PropositionPAE propPae;
	
	public Etudiant() {
		
	}
	
	public Etudiant(String nom, String matricule, String classe, int creditsValides, int creditTot, String moyenne) {
		super();
		this.nom_etudiant = nom;
		this.matricule = matricule;
		this.classe = classe;
		this.creditsValides = creditsValides;
		this.creditTot = creditTot;
		this.setMoyenne(moyenne);
		this.setDelibere(false);
		this.UE = new ArrayList<AssociationUE>();
		this.delibere = false;
		this.propPae = null;
	}
	
	public Etudiant(ParsedEtudiant etud, List<UniteEnseignement> ue) {
		this(etud.getNom(),etud.getMatricule(),etud.getClasse(),etud.getCreditsReussi(),etud.getCreditsTotaux(), etud.getMoyenne());
		for(ParsedAssociationUE p: etud.getListeUE()) {
			this.UE.add(new AssociationUE(p,ue));
		}
	}
	
	public void generateEtudiantPae(Section section) {
		this.propPae = PropositionPAE.generatePAE(this, section);
	}
	
	public PropositionPAE getPropPae() {
		return propPae;
	}

	public void setPropPae(PropositionPAE propPae) {
		this.propPae = propPae;
	}

	public void setUE(ArrayList<AssociationUE> uE) {
		UE = uE;
	}

	public Integer getId() {
		return id;
	}

	public String getNom() {
		return nom_etudiant;
	}

	public void setNom(String nom) {
		this.nom_etudiant = nom;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public int getCreditsValides() {
		int res = 0;
		for(AssociationUE aue: this.UE) {
			if(aue.isReussi()) {
				res+=aue.getUE().getTotalCredit();
			}
		}
		return res;
	}

	public void setCreditsValides(int creditsValides) {
		this.creditsValides = creditsValides;
	}

	public int getCreditTot() {
		return creditTot;
	}

	public void setCreditTot(int creditTot) {
		this.creditTot = creditTot;
	}	

	public List<AssociationUE> getUE() {
		Collections.sort(UE);
		return UE;
	}

	public boolean addUE(AssociationUE ue) {
		return this.UE.add(ue);
	}

	public boolean isDelibere() {
		return delibere;
	}
	
	public String isDelibereStringFormat() {
		if(isDelibere())
			return "Fait";
		else
			return "A faire";
	}

	public void setDelibere(boolean delibere) {
		this.delibere = delibere;
	}

	public String getMoyenne() {
		if(NumberUtils.isCreatable(moyenne)) {
			return Math.round((NumberUtils.createDouble(moyenne)*100.0)*100.0)/100.0+"%";
		}
		return moyenne;
	}
		
	public void switchDelib() {
		this.delibere = !delibere;
	}
	
	public void setMoyenne(String moyenne) {
		this.moyenne = moyenne;
	}
	
	public String getBlocSuivant() {
		switch(this.getClasse()) {
			case "1B":
				return "2B";
			case "2B":
				return "3B";
			default:
				return null;
		}
	}
	
	public void etudiantToPdf(Section s, PdfWriter writer) throws IOException
	{
        		
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        
        PdfFont ueF = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        PdfFont credTot = PdfFontFactory.createFont(StandardFonts.COURIER_BOLD);
        PdfFont aaF = PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
        
		Table table = new Table(1);
		Paragraph p = new Paragraph();
		
		p.add("Nom de l'étudiant: "+this.getNom() +"\n");
		p.add("Matricule: "+this.getMatricule() + "\n\n");
		p.add("Bachelier en "+s.pickRightName()+ "\n\n");
		
		for(PropositionUE propUe : this.getPropPae().getListeUE())
		{
			
			Text UeName = new Text("\nUE: "+propUe.getNom()).setFont(ueF);
			p.add(UeName);
			p.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
			p.add(new Tab());
			Text creditUe = new Text(propUe.getTotalCredit()+" crédits\n\n").setFont(ueF);
			p.add(creditUe);
			
			for(PropositionAA propAa : propUe.getListeAA())
			{
				Text AaName = new Text("    AA: "+propAa.getNom()).setFont(aaF);
				p.add(AaName);
				
				if(!propAa.isDispense())
				{
					p.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
					p.add(new Tab());
					Text creditAa = new Text(propAa.getCredits()+"crédits\n\n").setFont(aaF);
					p.add(creditAa);
				}
				else
				{
					p.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
					p.add(new Tab());
					Text dispense = new Text("dispensé\n\n").setFont(aaF);
					p.add(dispense);
				}
			}
		}
		
		p.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
		p.add(new Tab());
		Text totCredit = new Text("Crédits totaux: "+this.getPropPae().getCreditTotPropPae() + "\n\n").setFont(credTot);
		p.add(totCredit);
		table.addCell(p);
		document.add(table);
		document.close();

	}
	
	@Override
	public String toString() 
	{
		return "Etudiant [nom=" + nom_etudiant + ", matricule=" + matricule + ", classe=" + classe + ", creditsValides="
				+ creditsValides + ", creditTot=" + creditTot + "]";
	}

	
}
