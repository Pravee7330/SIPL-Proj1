package com.nt.Util;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nt.model.Vehicle;

@Component
public class PdfGenerator {

	public void generatePdf( List<Vehicle> vehicles, HttpServletResponse httpServletResponse , LocalDate startDate , LocalDate endDate) throws DocumentException,IOException{
		Document document = new Document(PageSize.A4);
		
		PdfWriter.getInstance(document, httpServletResponse.getOutputStream());
		document.open();
		
		Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTitle.setSize(20);
		
		Paragraph p1 = new Paragraph("List of vehicles from Date : " + startDate + "-" + endDate , fontTitle);
		p1.setAlignment(Paragraph.ALIGN_CENTER);
		
		document.add(p1);
		
		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100f);
		table.setWidths(new int[] {1,3,3,3,3,3});
		table.setSpacingBefore(5);
		
		PdfPCell cell = new PdfPCell();
		
		cell.setBackgroundColor(CMYKColor.gray);
		cell.setPadding(5);
		
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
	    font.setColor(CMYKColor.WHITE);
	    
	    cell.setPhrase(new Phrase("ID", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Brand", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Reg_num", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Color", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Vehicle_type", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Weight", font));
	    table.addCell(cell);
	
	    for(Vehicle vehicle: vehicles ) {
	    	table.addCell(String.valueOf(vehicle.getId()));
	    	table.addCell(String.valueOf(vehicle.getBrand()));
	    	table.addCell(String.valueOf(vehicle.getRegistrationNumber()));
	    	table.addCell(String.valueOf(vehicle.getColor()));
	    	table.addCell(String.valueOf(vehicle.getVehicleType()));
	    	table.addCell(String.valueOf(vehicle.getWeight()));
	    	
	    }
	    
	    document.add(table);
	    
	    document.close();
	}
}
