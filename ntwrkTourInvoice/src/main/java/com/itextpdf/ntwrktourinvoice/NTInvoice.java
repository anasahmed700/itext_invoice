package com.itextpdf.ntwrktourinvoice;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class NTInvoice {
    public static final String RESULT = "ntc_invoice.pdf";
    
    static NTInvoiceData data = new NTInvoiceData();
    
    public static void main(String[] args) {
        
        data.setDate("15-02-2018");
        data.setInvoice_num("01");
        data.setInvoice_to("M/S. AL TAWAKKAL AUTOS KARACHI");
        data.setReceivable("258,395");
        
        ArrayList<NTInvoiceDetail> detailsList = new ArrayList();
        
        NTInvoiceDetail data1List = new NTInvoiceDetail();
        data1List.setSerial_no("1");
        data1List.setCustomer_name("KARACHI AUTOS (CNG)");
        data1List.setCity("KHI");
        data1List.setBusiness_amount("555,555");
        data1List.setScheme_payment_amount("1,389");
        data1List.setRemarks("");
        detailsList.add(data1List);
        
        NTInvoiceDetail data2List = new NTInvoiceDetail();
        data2List.setSerial_no("2");
        data2List.setCustomer_name("LIFAN CENTER");
        data2List.setCity("HYD");
        data2List.setBusiness_amount("5,861,220");
        data2List.setScheme_payment_amount("14,653");
        data2List.setRemarks("");
        detailsList.add(data2List);
        
        NTInvoiceDetail data3List = new NTInvoiceDetail();
        data3List.setSerial_no("3");
        data3List.setCustomer_name("UMER WALEED AUTOS");
        data3List.setCity("SDQ");
        data3List.setBusiness_amount("18,141,135");
        data3List.setScheme_payment_amount("70,353");
        data3List.setRemarks("PENALTY ADJUSTMENT");
        detailsList.add(data3List);
        
        NTInvoiceDetail data4List = new NTInvoiceDetail();
        data4List.setSerial_no("4");
        data4List.setCustomer_name("AL-TAWAKKAL AUTOS- MR. HARIS");
        data4List.setCity("KHI");
        data4List.setBusiness_amount("");
        data4List.setScheme_payment_amount("100,000");
        data4List.setRemarks("TOUR ADJT. MALAYSIA");
        detailsList.add(data4List);
        
        NTInvoiceDetail data5List = new NTInvoiceDetail();
        data5List.setSerial_no("5");
        data5List.setCustomer_name("AL-TAWAKKAL AUTOS- MR. ABDULLAH");
        data5List.setCity("KHI");
        data5List.setBusiness_amount("");
        data5List.setScheme_payment_amount("72,000");
        data5List.setRemarks("TOUR ADJT. THAILAND");
        detailsList.add(data5List);
        
        data.setListOfDetails(detailsList);
        new NTInvoice().createPdf(RESULT);
    }

    public void createPdf(String result){
        try {
            Document document = new Document();
            document.setPageSize(PageSize.A4);
            document.setMargins(15, 15, 20, 20);
            PdfWriter.getInstance(document, new FileOutputStream(result));
            document.open();
            // Heading
            PdfPTable heading = new PdfPTable(1);
            PdfPCell cell = new PdfPCell(new Phrase("FOREIGN TOUR SCHEME-2017",
                    FontFactory.getFont(result, result, true, 17, Font.NORMAL, BaseColor.BLACK)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            heading.addCell(cell);
            cell = new PdfPCell(new Phrase("NETWORK TOUR CONTRIBUTION INVOICE",
                    FontFactory.getFont(result, result, true, 15, Font.NORMAL, BaseColor.WHITE)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.BLACK);
            heading.setWidthPercentage(160/1.6f);
            heading.addCell(cell);
            
            // Date table
            PdfPTable dateTable = new PdfPTable(2);
            dateTable.setWidths(new float[]{1, 1.2f});
            dateTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
            dateTable.setWidthPercentage(160/7.23f);
            borderLessCell(dateTable, "DATE", 'R');
            fieldCell(dateTable, data.getDate(), 'C');
            borderLessCell(dateTable, "INVOICE#", 'R');
            fieldCell(dateTable, data.getInvoice_num(), 'C');
            
            // Customer info Table
            PdfPTable custTable = new PdfPTable(1);
            custTable.setWidthPercentage(160/6.5f);
            custTable.setHorizontalAlignment(Element.ALIGN_LEFT);
            headerCell(custTable, "INVOICE TO", 'L');
            fieldCell(custTable, data.getInvoice_to(), 'L');
            
            // Table for Records
            PdfPTable rcrdTable = new PdfPTable(6);
            rcrdTable.setWidths(new float[]{1, 5, 1, 2, 2, 3});
            rcrdTable.setWidthPercentage(160/1.6f);
            // Table headers
            headerCell(rcrdTable, "S NO", 'c');
            headerCell(rcrdTable, "CUSTOMER NAME", 'C');
            headerCell(rcrdTable, "CITY", 'c');
            headerCell(rcrdTable, "BUSINESS AMOUNT", 'c');
            headerCell(rcrdTable, "SCHEME PAYMENT AMOUNT @ 0.25%", 'C');
            headerCell(rcrdTable, "REMARKS", 'C');
            //Table fields
            for (NTInvoiceDetail obj : data.getListOfDetails()){
                fieldCell(rcrdTable, obj.getSerial_no(), 'c');
                fieldCell(rcrdTable, obj.getCustomer_name(), 'L');
                fieldCell(rcrdTable, obj.getCity(), 'c');
                fieldCell(rcrdTable, obj.getBusiness_amount(), 'R');
                fieldCell(rcrdTable, obj.getScheme_payment_amount(), 'R');
                fieldCell(rcrdTable, obj.getRemarks(), 'c');
            }
            // colspan cell
            PdfPCell colspanCell = new PdfPCell();
            colspanCell.setColspan(3);
            colspanCell.setBorder(PdfPCell.BODY);
            rcrdTable.addCell(colspanCell);
            colspanCell = new PdfPCell(new Phrase("24,557,910", FontFactory.getFont(FontFactory.COURIER, 10)));
            colspanCell.setBorder(PdfPCell.BOTTOM);
            colspanCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            rcrdTable.addCell(colspanCell);
            colspanCell = new PdfPCell(new Phrase("258,395", FontFactory.getFont(FontFactory.COURIER, 10)));
            colspanCell.setBorder(PdfPCell.BOTTOM);
            colspanCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            rcrdTable.addCell(colspanCell);
            colspanCell = new PdfPCell();
            colspanCell.setBorder(PdfPCell.CHUNK);
            rcrdTable.addCell(colspanCell);
            
            PdfPTable lastTable = PdfPTable.shallowCopy(rcrdTable);
            colspanCell = new PdfPCell();
            colspanCell.setColspan(3);
            colspanCell.setBorder(PdfPCell.NO_BORDER);
            lastTable.addCell(colspanCell);
            borderLessCell(lastTable, "RECEIVABLE", 'c');
            highLightCell(lastTable, data.getReceivable(), 'R');
            borderLessCell(lastTable, "", 'c');
            
            document.add(heading);
            document.add(dateTable);
            document.add(custTable);
            document.add(rcrdTable);
            document.add(new Phrase(Chunk.NEWLINE));
            document.add(lastTable);
            document.add(new Phrase(Chunk.NEWLINE));
            document.add(new Phrase(Chunk.NEWLINE ));

            Paragraph para = new Paragraph("If you have any question about this invoice, Please contact\n"
                    + "Mobile # 0345-8230212, 0321-8230086 E-mail: mrd@crowngroup.com.pk"
                    , FontFactory.getFont(FontFactory.TIMES, 8));
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            para = new Paragraph("Thank You For Your Business!",
                    FontFactory.getFont(FontFactory.TIMES, 10, Font.BOLDITALIC, BaseColor.BLUE));
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            
            para = new Paragraph("Page 2",
                    FontFactory.getFont(FontFactory.TIMES, 10, Font.BOLDITALIC, BaseColor.BLACK));
            para.setAlignment(Element.ALIGN_RIGHT);
            document.add(para);
            
            document.close();
            JOptionPane.showMessageDialog(null, "Report Saved!");
        } catch (FileNotFoundException | DocumentException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public PdfPCell fieldCell(PdfPTable table, String value, char align){
        PdfPCell cell = new PdfPCell(new Phrase(value, FontFactory.getFont(FontFactory.COURIER, 10)));
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
    
    
    public PdfPCell headerCell(PdfPTable table, String value, char align){
        PdfPCell cell = new PdfPCell(new Phrase(value, FontFactory.getFont(FontFactory.COURIER_BOLD, 12, BaseColor.BLACK)));
        cell.setBackgroundColor(BaseColor.GRAY);
        
        switch (align) {
            case 'L':
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                break;
            case 'R':
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                break;
            default:
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                break;
        }
        return table.addCell(cell);
    }
    public PdfPCell borderLessCell(PdfPTable table, String value, char align){
        PdfPCell cell = new PdfPCell(new Phrase(value, FontFactory.getFont(FontFactory.COURIER, 10)));
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
    public PdfPCell highLightCell(PdfPTable table, String value, char align){
        PdfPCell cell = new PdfPCell(new Phrase(value, FontFactory.getFont(FontFactory.COURIER, 10)));
        cell.setBorder(PdfPCell.BOTTOM);
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
