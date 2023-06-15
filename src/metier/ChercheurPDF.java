package metier;

import java.util.List;
import java.awt.Color;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import DAO.GestionChercheur;
import metier.chercheur;
public class ChercheurPDF {
	private List<chercheur> listChercheur;

	public ChercheurPDF(List<chercheur> listChercheur) {
		super();
		this.listChercheur = listChercheur;
	}

	public List<chercheur> getListChercheur() {
		return listChercheur;
	}

	public void setListChercheur(List<chercheur> listChercheur) {
		this.listChercheur = listChercheur;
	}
	private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.green);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Nom", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Prenom", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Email", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Profile", font));
        table.addCell(cell);    
        
    }
	private void writeTableData(PdfPTable table) {
        for (chercheur user : listChercheur) {
            table.addCell(user.getUsername());
            table.addCell(user.getPrenom());
            table.addCell(user.getEmail());
            table.addCell(user.getProfil());
            
           
            
            
        }
    }
	public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("Liste des chercheurs ", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
	
	
	

}

