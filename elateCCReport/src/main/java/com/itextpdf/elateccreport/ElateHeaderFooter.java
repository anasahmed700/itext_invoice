package com.itextpdf.elateccreport;

import com.itextpdf.elateccreport.ReportData;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;


public class ElateHeaderFooter extends PdfPageEventHelper {
        protected PdfPTable header;
        protected PdfPTable footer;
        protected float tableHeight;
        
        public ElateHeaderFooter() throws IOException, BadElementException {
            // header table
            header = new PdfPTable(1);
            header.setTotalWidth(565);
            header.setLockedWidth(true);
            Image image = Image.getInstance(Report.data.getElata_logo());
            image.scaleAbsolute(200, 200);
            PdfPCell cell = new PdfPCell();           
            cell.setBorder(PdfPCell.BOTTOM);
            cell.setBorderColor(BaseColor.RED);
            Paragraph para = new Paragraph();
            para.add(new Chunk(image,0,0));
            para.add(new Phrase(Report.data.getHeading(),
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 22, Font.BOLDITALIC, BaseColor.BLUE)));

            para.setAlignment(Element.ALIGN_CENTER);
            cell.addElement(para);
            header.addCell(cell);
            // Heading Discription
            cell = new PdfPCell(new Paragraph(Report.data.getHeading_description(),
                    FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL, BaseColor.BLUE)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(PdfPCell.NO_BORDER);
            header.setWidthPercentage(160 / 2.7f);
            header.addCell(cell);
            tableHeight = header.getTotalHeight();
            
            // footer table
            footer = new PdfPTable(1);
            footer.setTotalWidth(565);
            footer.setLockedWidth(true);
            Image locationIcon = Image.getInstance(Report.data.getLocation_icon());
            locationIcon.scaleAbsolute(10, 10);
            Image phoneIcon = Image.getInstance(Report.data.getPhone_icon());
            phoneIcon.scaleAbsolute(10, 10);
            Image faxIcon = Image.getInstance(Report.data.getFax_icon());
            faxIcon.scaleAbsolute(10, 10);
            Image emailIcon = Image.getInstance(Report.data.getEmail_icon());
            emailIcon.scaleAbsolute(10, 10);
            Image webIcon = Image.getInstance(Report.data.getWeb_icon());
            webIcon.scaleAbsolute(10, 10);

            PdfPCell footCell = new PdfPCell();           
            footCell.setBorder(PdfPCell.TOP);
            footCell.setBorderColor(BaseColor.RED);
            Paragraph footPara = new Paragraph();
            footPara.add(new Chunk(locationIcon, 0, 0));
            footPara.add(new Phrase(Report.data.getHead_office(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLUE)));
            footPara.add(new Chunk(phoneIcon,0,0));
            footPara.add(new Phrase(": "+Report.data.getHO_phone()+"\n", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLUE)));
            footPara.add(new Chunk(faxIcon,0,0));
            footPara.add(new Phrase(" "+Report.data.getFax()+" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLUE)));
            footPara.add(new Chunk(emailIcon,0,0));
            footPara.add(new Phrase(": "+Report.data.getEmail()+" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLUE)));
            footPara.add(new Chunk(webIcon,0,0));
            footPara.add(new Phrase(": "+Report.data.getWeb()+"\n", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLUE)));
            footPara.add(new Chunk(locationIcon,0,0));
            footPara.add(new Phrase(Report.data.getBranch_office()+" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLUE)));
            footPara.add(new Chunk(phoneIcon,0,0));
            footPara.add(new Phrase(": "+Report.data.getBO_phone(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLUE)));
                    
            footPara.setAlignment(Element.ALIGN_CENTER);
            footCell.addElement(footPara);
            footCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            footer.setHorizontalAlignment(PdfPTable.ALIGN_CENTER);
            footer.addCell(footCell);
            footer.setWidthPercentage(160/2.7f);
        }
        public float getTableHeight() {
            return tableHeight;
        }
       
        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            header.writeSelectedRows(0, -1,
                    document.left(),
                    document.top() + ((document.topMargin() + tableHeight) / 3),
                    writer.getDirectContent());
        }
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte canvas = writer.getDirectContent();
            CMYKColor redColor = new CMYKColor(0.f, 1.f, 1.f, 0.f);
            canvas.setColorStroke(redColor);
            canvas.moveTo(document.left(), 80);
            canvas.lineTo(document.right(), 80);
            canvas.moveTo(document.left(), 80);
            canvas.lineTo(document.right(), 80);
            canvas.closePathStroke();
            footer.writeSelectedRows(0, -1, document.left(), document.bottom()+ (document.bottomMargin() / 22), writer.getDirectContent());
        }
    }