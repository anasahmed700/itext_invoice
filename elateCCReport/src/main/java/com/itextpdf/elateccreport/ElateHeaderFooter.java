/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itextpdf.elateccreport;

import static com.itextpdf.elateccreport.Report.data;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.IOException;

/**
 *
 * @author Anas
 */
public class ElateHeaderFooter {
    public PdfPTable header() throws BadElementException, IOException{
        PdfPTable header = new PdfPTable(1);
        Image logo = Image.getInstance("resources\\elatelogo.png");
        logo.scaleAbsolute(150, 120);
        PdfPCell cell = new PdfPCell();           
        cell.setBorder(PdfPCell.BOTTOM);
        cell.setBorderColor(BaseColor.RED);
        Paragraph para = new Paragraph();
        para.add(new Chunk(logo,0,0));
        para.add(new Phrase("ELATE C.C (PVT) LTD.",
                FontFactory.getFont(FontFactory.TIMES_ROMAN, 17, Font.BOLDITALIC, BaseColor.BLUE)));
            
        para.setAlignment(Element.ALIGN_CENTER);
        cell.addElement(para);
        header.addCell(cell);
        // Heading Discription
        cell = new PdfPCell(new Paragraph("DEALS IN MEDICAL EQUIPMENTS & SURGICAL DISPOSABLES",
                FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL, BaseColor.BLUE)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(PdfPCell.NO_BORDER);
        header.setWidthPercentage(160 / 2.7f);
        header.addCell(cell);
        return header;
    }
    
    public PdfPTable footer() throws BadElementException, IOException{
        PdfPTable footer = new PdfPTable(1);
            Image locationIcon = Image.getInstance("resources\\location.png");
            locationIcon.scaleAbsolute(10, 10);
            Image phoneIcon = Image.getInstance("resources\\phone.png");
            phoneIcon.scaleAbsolute(10, 10);
            Image faxIcon = Image.getInstance("resources\\fax.png");
            faxIcon.scaleAbsolute(10, 10);
            Image emailIcon = Image.getInstance("resources\\email.png");
            emailIcon.scaleAbsolute(10, 10);
            Image webIcon = Image.getInstance("resources\\web.png");
            webIcon.scaleAbsolute(10, 10);

            PdfPCell footCell = new PdfPCell();           
            footCell.setBorder(PdfPCell.TOP);
            footCell.setBorderColor(BaseColor.RED);
            Paragraph footPara = new Paragraph();
            footPara.add(new Chunk(locationIcon, 0, 0));
            footPara.add(new Phrase(data.getHead_office(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLUE)));
            footPara.add(new Chunk(phoneIcon,0,0));
            footPara.add(new Phrase(":"+data.getHO_phone()+"\n", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLUE)));
            footPara.add(new Chunk(faxIcon,0,0));
            footPara.add(new Phrase(" "+data.getFax()+" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLUE)));
            footPara.add(new Chunk(emailIcon,0,0));
            footPara.add(new Phrase(": "+data.getEmail()+" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLUE)));
            footPara.add(new Chunk(webIcon,0,0));
            footPara.add(new Phrase(": "+data.getWeb()+"\n", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLUE)));
            footPara.add(new Chunk(locationIcon,0,0));
            footPara.add(new Phrase(data.getBranch_office()+" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLUE)));
            footPara.add(new Chunk(phoneIcon,0,0));
            footPara.add(new Phrase(": "+data.getBO_phone(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLUE)));
                    
            footPara.setAlignment(Element.ALIGN_CENTER);
            footCell.addElement(footPara);
            footCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            footer.addCell(footCell);
            footer.setWidthPercentage(100);
//            footer.setExtendLastRow(true);
            footer.setSpacingBefore(178);
            footer.setPaddingTop(100);
            
            return footer;
    }
}
