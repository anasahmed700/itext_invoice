package com.itextpdf.first_invoice;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;

public class Invoice {

    public static final String RESULT = "invoice.pdf";

    static InvoiceData data;

    public static void main(String[] args) {
        data = new InvoiceData();
        data.setInvoice_to("AL TAWAKKAL AUTOS KARACHI");
        data.setDate("15-02-2018");
        data.setInvoice_num("52");
        data.setRegion("KARACHI");
        data.setSerial_no("1");
        data.setCustomer_name("AL MAKKAH AUTOS");
        data.setCity("KARACHI");
        data.setTour("THAILAND");
        data.setBusiness_amount("3640000");
        data.setScheme_payment_amount("72800");
        data.setEncashOrExcess_amount("800");
        data.setRemarks("Excess");
        
        new Invoice().createPDF(RESULT);

    }

    public void createPDF(String result) {
        try {
            Document document = new Document();
            document.setPageSize(PageSize.A4);
            document.setMargins(15, 15, 20, 20);
            PdfWriter.getInstance(document, new FileOutputStream(result));
            document.open();
            
            PdfPTable table = new PdfPTable(1);
//            table.setWidths(new int[]{1,3,2,2,2,2,2,2});
            PdfPCell cell = new PdfPCell(new Paragraph("FOREIGN TOUR SCHEME-2017",
                    FontFactory.getFont(result, result, true, 15, Font.NORMAL, BaseColor.BLACK)));
//            cell.setColspan(8);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("DYNAMIC TOUR CONTRIBUTION INVOICE",
                    FontFactory.getFont(result, result, true, 10, Font.NORMAL, BaseColor.WHITE)));
            cell.setColspan(8);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.BLACK);
            table.setWidthPercentage(160/1.6f);
            table.addCell(cell);
            
            PdfPTable table1 = new PdfPTable(2);
            table1.setWidths(new float[]{1,1.2f});
            table1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table1.setWidthPercentage(160 / 8.23f);
            PdfPCell cell1 = new PdfPCell(new Phrase("Date", FontFactory.getFont(FontFactory.COURIER, 8)));
//            cell1.disableBorderSide(0);
            PdfPCell cell1_2 = new PdfPCell(new Phrase(data.getDate(), FontFactory.getFont(FontFactory.COURIER, 8)));
            PdfPCell cell1_3 = new PdfPCell(new Phrase("Invoice #", FontFactory.getFont(FontFactory.COURIER, 8)));
            PdfPCell cell1_4 = new PdfPCell(new Phrase(data.getInvoice_num(), FontFactory.getFont(FontFactory.COURIER, 8)));
            PdfPCell cell1_5 = new PdfPCell(new Phrase("Region", FontFactory.getFont(FontFactory.COURIER, 8)));
            PdfPCell cell1_6 = new PdfPCell(new Phrase(data.getRegion(), FontFactory.getFont(FontFactory.COURIER, 8)));
            table1.addCell(cell1);
            table1.addCell(cell1_2);
            table1.addCell(cell1_3);
            table1.addCell(cell1_4);
            table1.addCell(cell1_5);
            table1.addCell(cell1_6);

            PdfPTable table2 = new PdfPTable(1);
            table2.setHorizontalAlignment(Element.ALIGN_LEFT);
            table2.setWidthPercentage(160/6.5f);
            PdfPCell cell2 = new PdfPCell(new Phrase("INVOICE TO", FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.WHITE)));
            cell2.setBackgroundColor(BaseColor.BLACK);
            table2.addCell(cell2);
            table2.addCell(new Phrase(data.getInvoice_to(), FontFactory.getFont(FontFactory.COURIER, 8)));
            
            PdfPTable table3 = new PdfPTable(8);
            table3.setWidths(new int[]{1,4,2,2,2,2,2,2});
            table3.setWidthPercentage(160/1.6f);
            
            headerCell(table3, "S NO");
            headerCell(table3, "CUSTOMER NAME");
            headerCell(table3, "CITY");
            headerCell(table3, "TOUR");
            headerCell(table3, "BUSINESS AMOUNT");
            headerCell(table3, "SCHEME PAYMENT AMOUNT");
            headerCell(table3, "ENCASH/EXCESS AMOUNT");
            headerCell(table3, "REMARKS");
            fieldCell(table3, data.getSerial_no(), "right");
            fieldCell(table3, data.getCustomer_name(), "right");
            fieldCell(table3, data.getCity(), "right");
            fieldCell(table3, data.getTour(), "right");
            fieldCell(table3, data.getBusiness_amount(), "right");
            fieldCell(table3, data.getScheme_payment_amount(), "right");
            fieldCell(table3, data.getEncashOrExcess_amount(), "right");
            fieldCell(table3, data.getRemarks(), "right");

            document.add(table);
            document.add(table1);
            document.add(table2);
            document.add(table3);
            
            document.close();

            JOptionPane.showMessageDialog(null, "Report saved!");
        } catch (FileNotFoundException | DocumentException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public PdfPCell headerCell(PdfPTable table, String value){
        PdfPCell cell = new PdfPCell(new Phrase(value, FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.WHITE)));
        cell.setBackgroundColor(BaseColor.BLACK);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        return table.addCell(cell);
    }
    public PdfPCell fieldCell(PdfPTable table, String value, String align){
        PdfPCell cell = new PdfPCell(new Phrase(value, FontFactory.getFont(FontFactory.COURIER, 8)));
        switch (align) {
            case "left":
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                break;
            case "right":
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                break;
            default:
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                break;
        }
        return table.addCell(cell);
    }
}
