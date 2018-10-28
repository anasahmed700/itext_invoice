/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itextpdf.elateccreport;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import javax.swing.JOptionPane;

/**
 *
 * @author Anas
 */
public class Report {
    public static final String RESULT = "report.pdf";
    static ReportData data = new ReportData();
    
    public static void main(String[] args) throws BadElementException, IOException {
        
        data.setCust_selection("CodenCode Software Solution\nKarachi");
        data.setDate("18-sep-2018");
        data.setProduct_code("AEX-001");
        data.setProduct_name("MEHRAN");
        data.setPrice("120,000");
        new Report().createPdf(RESULT);
    }
    
    public void createPdf(String result) throws BadElementException, IOException{
        try {
            Document document = new Document();
            document.setPageSize(PageSize.A4);
            document.setMargins(15, 15, 20, 20);
            PdfWriter.getInstance(document, new FileOutputStream(result));
            
            document.open();
            // Heading
            PdfPTable table = new PdfPTable(1);
            Image image = Image.getInstance("logo.png");
            image.scaleAbsolute(140, 110);
            
            PdfPCell cell = new PdfPCell();           
            cell.setBorder(PdfPCell.BOTTOM);
            Paragraph para = new Paragraph();
            para.add(new Chunk(image,0,0));
            para.add(new Phrase("EXTREME MOTORS (PVT) LTD.",
                    FontFactory.getFont(result, result, true, 17, Font.NORMAL)));
            para.setAlignment(Element.ALIGN_CENTER);
            cell.addElement(para);
            table.addCell(cell);
            
            cell = new PdfPCell(new Paragraph("Deals in all kind of vehicles, manual & auto transmission system & vehicles body parts",
                    FontFactory.getFont(result, result, true, 8, Font.NORMAL)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(PdfPCell.NO_BORDER);
            table.setWidthPercentage(160 / 2.6f);
            table.addCell(cell);
            
            PdfPTable custDateTable = new PdfPTable(2);
            borderLessCell(custDateTable, "To,", 'L');
            borderLessCell(custDateTable, "Date", 'R');
            borderLessCell(custDateTable, data.getCust_selection(), 'L');
            borderLessCell(custDateTable, data.getDate(), 'R');
            custDateTable.setWidthPercentage(100);
            
            
            
            PdfPTable table1 = new PdfPTable(1);
            cell = new PdfPCell(new Phrase("Quotation", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(PdfPCell.BOTTOM);
            table1.setWidthPercentage(18);
            table1.addCell(cell);
            // setting image file
            image = Image.getInstance("mehran.jpg");
            image.scaleAbsolute(140, 110);
            image.setAlignment(Element.ALIGN_CENTER);
            
            document.add(table);
            document.add(new Phrase(Chunk.NEWLINE));
            document.add(custDateTable);
            para = new Paragraph("Product Code  : ");
            para.add(new Phrase(data.getProduct_code()));
            document.add(para);
            para = new Paragraph("Product Name : ");
            para.add(new Phrase(data.getProduct_name()));
            document.add(para);
            document.add(table1);
            document.add(image);
            HTMLWorker htmlWorker = new HTMLWorker(document);
            //html parsing
            String htmlString = "<P STYLE=\"MARGIN: 0PX; PADDING: 6PX 0PX; BORDER: 0PX; OUTLINE: NONE; FONT-SIZE: 12PX; COLOR: RGB(66, 66, 66); LINE-HEIGHT: 18PX; FONT-FAMILY: CALIBRI, TAHOMA; FONT-STYLE: NORMAL; FONT-VARIANT-LIGATURES: NORMAL; FONT-VARIANT-CAPS: NORMAL; FONT-WEIGHT: 400; LETTER-SPACING: NORMAL; ORPHANS: 2; TEXT-ALIGN: START; TEXT-INDENT: 0PX; TEXT-TRANSFORM: NONE; WHITE-SPACE: NORMAL; WIDOWS: 2; WORD-SPACING: 0PX; -WEBKIT-TEXT-STROKE-WIDTH: 0PX; BACKGROUND-COLOR: RGB(255, 255, 255); TEXT-DECORATION-STYLE: INITIAL; TEXT-DECORATION-COLOR: INITIAL;\"><SPAN STYLE=\"FONT-SIZE: LARGE; COLOR: RGB(255, 0, 0);\">MODELS AVAILABLE<SPAN></SPAN></SPAN><SPAN></SPAN><BR><BR><SPAN STYLE=\"FONT-SIZE: MEDIUM;\">MEHRAN VX EURO 2</SPAN></P><P STYLE=\"MARGIN: 0PX; PADDING: 6PX 0PX; BORDER: 0PX; OUTLINE: NONE; FONT-SIZE: 12PX; COLOR: RGB(66, 66, 66); LINE-HEIGHT: 18PX; FONT-FAMILY: CALIBRI, TAHOMA; FONT-STYLE: NORMAL; FONT-VARIANT-LIGATURES: NORMAL; FONT-VARIANT-CAPS: NORMAL; FONT-WEIGHT: 400; LETTER-SPACING: NORMAL; ORPHANS: 2; TEXT-ALIGN: START; TEXT-INDENT: 0PX; TEXT-TRANSFORM: NONE; WHITE-SPACE: NORMAL; WIDOWS: 2; WORD-SPACING: 0PX; -WEBKIT-TEXT-STROKE-WIDTH: 0PX; BACKGROUND-COLOR: RGB(255, 255, 255); TEXT-DECORATION-STYLE: INITIAL; TEXT-DECORATION-COLOR: INITIAL;\"><SPAN STYLE=\"FONT-SIZE: MEDIUM;\">MEHRAN VXR EURO 2</SPAN></P><P STYLE=\"MARGIN: 0PX; PADDING: 6PX 0PX; BORDER: 0PX; OUTLINE: NONE; FONT-SIZE: 12PX; COLOR: RGB(66, 66, 66); LINE-HEIGHT: 18PX; FONT-FAMILY: CALIBRI, TAHOMA; FONT-STYLE: NORMAL; FONT-VARIANT-LIGATURES: NORMAL; FONT-VARIANT-CAPS: NORMAL; FONT-WEIGHT: 400; LETTER-SPACING: NORMAL; ORPHANS: 2; TEXT-ALIGN: START; TEXT-INDENT: 0PX; TEXT-TRANSFORM: NONE; WHITE-SPACE: NORMAL; WIDOWS: 2; WORD-SPACING: 0PX; -WEBKIT-TEXT-STROKE-WIDTH: 0PX; BACKGROUND-COLOR: RGB(255, 255, 255); TEXT-DECORATION-STYLE: INITIAL; TEXT-DECORATION-COLOR: INITIAL;\"></P><P STYLE=\"MARGIN: 0PX; PADDING: 6PX 0PX; BORDER: 0PX; OUTLINE: NONE; FONT-SIZE: 12PX; COLOR: RGB(66, 66, 66); LINE-HEIGHT: 18PX; FONT-FAMILY: CALIBRI, TAHOMA; FONT-STYLE: NORMAL; FONT-VARIANT-LIGATURES: NORMAL; FONT-VARIANT-CAPS: NORMAL; FONT-WEIGHT: 400; LETTER-SPACING: NORMAL; ORPHANS: 2; TEXT-ALIGN: START; TEXT-INDENT: 0PX; TEXT-TRANSFORM: NONE; WHITE-SPACE: NORMAL; WIDOWS: 2; WORD-SPACING: 0PX; -WEBKIT-TEXT-STROKE-WIDTH: 0PX; BACKGROUND-COLOR: RGB(255, 255, 255); TEXT-DECORATION-STYLE: INITIAL; TEXT-DECORATION-COLOR: INITIAL;\"><SPAN STYLE=\"COLOR: RGB(255, 0, 0); FONT-SIZE: LARGE;\">COLORS</SPAN></P><UL STYLE=\"MARGIN: 5PX 0PX 5PX 25PX; PADDING: 0PX; BORDER: 0PX; OUTLINE: NONE; FONT-SIZE: 12PX; COLOR: RGB(66, 66, 66); LINE-HEIGHT: 20PX; FONT-FAMILY: CALIBRI, TAHOMA; FONT-STYLE: NORMAL; FONT-VARIANT-LIGATURES: NORMAL; FONT-VARIANT-CAPS: NORMAL; FONT-WEIGHT: 400; LETTER-SPACING: NORMAL; ORPHANS: 2; TEXT-ALIGN: START; TEXT-INDENT: 0PX; TEXT-TRANSFORM: NONE; WHITE-SPACE: NORMAL; WIDOWS: 2; WORD-SPACING: 0PX; -WEBKIT-TEXT-STROKE-WIDTH: 0PX; BACKGROUND-COLOR: RGB(255, 255, 255); TEXT-DECORATION-STYLE: INITIAL; TEXT-DECORATION-COLOR: INITIAL;\"><LI STYLE=\"MARGIN: 0PX; PADDING: 0PX; BORDER: 0PX; OUTLINE: NONE;\"><SPAN STYLE=\"FONT-SIZE: MEDIUM;\">WHITE</SPAN></LI><LI STYLE=\"MARGIN: 0PX; PADDING: 0PX; BORDER: 0PX; OUTLINE: NONE;\"><SPAN STYLE=\"FONT-SIZE: MEDIUM;\">PEARL RED</SPAN></LI><LI STYLE=\"MARGIN: 0PX; PADDING: 0PX; BORDER: 0PX; OUTLINE: NONE;\"><SPAN STYLE=\"FONT-SIZE: MEDIUM;\">GRAPHITE GREY</SPAN></LI><LI STYLE=\"MARGIN: 0PX; PADDING: 0PX; BORDER: 0PX; OUTLINE: NONE;\"><SPAN STYLE=\"FONT-SIZE: MEDIUM;\">SMOKE GREEN</SPAN></LI><LI STYLE=\"MARGIN: 0PX; PADDING: 0PX; BORDER: 0PX; OUTLINE: NONE;\"><SPAN STYLE=\"FONT-SIZE: MEDIUM;\">EMINENT BLUE</SPAN></LI><LI STYLE=\"MARGIN: 0PX; PADDING: 0PX; BORDER: 0PX; OUTLINE: NONE;\"><SPAN STYLE=\"FONT-SIZE: MEDIUM;\">SILKY SILVER</SPAN></LI></UL>";
            htmlWorker.parse(new StringReader(htmlString));
            para = new Paragraph("PRICE: ");
            para.add(new Phrase(data.getPrice()));
            para.setAlignment(Element.ALIGN_RIGHT);
            document.add(para);
            
            document.close();
            JOptionPane.showMessageDialog(null, "Report Saved!");
        } catch (FileNotFoundException | DocumentException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public PdfPCell borderLessCell(PdfPTable table, String value, char align){
        PdfPCell cell = new PdfPCell(new Phrase(value));
        cell.setBorder(PdfPCell.NO_BORDER);
        switch (align) {
            case 'L':
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                break;
            case 'R':
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                break;
            default:
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                break;
        }
        return table.addCell(cell);
    }
}
