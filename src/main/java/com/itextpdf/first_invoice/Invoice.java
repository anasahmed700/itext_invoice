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
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Invoice {

    public static final String RESULT = "E:/invoice.pdf";

    static InvoiceData data = new InvoiceData();;

    public static void main(String[] args) {
        

        data.setInvoice_to("AL TAWAKKAL AUTOS KARACHI");
        data.setDate("15-02-2018");
        data.setInvoice_num("52");
        data.setRegion("KARACHI");

        ArrayList<InvoiceDetail> detailList = new ArrayList();

        InvoiceDetail data1List = new InvoiceDetail();

        data1List.setSerial_no("1");
        data1List.setCustomer_name("AL MAKKAH AUTOS");
        data1List.setCity("KARACHI");
        data1List.setTour("THAILAND");
        data1List.setBusiness_amount("3,640,000");
        data1List.setScheme_payment_amount("72,800");
        data1List.setEncashOrExcess_amount("800");
        data1List.setRemarks("Excess");
        detailList.add(data1List);

        InvoiceDetail data2List = new InvoiceDetail();

        data2List.setSerial_no("2");
        data2List.setCustomer_name("ASIA AUTOS");
        data2List.setCity("KARACHI");
        data2List.setTour("MALAYSIA");
        data2List.setBusiness_amount("214,700");
        data2List.setScheme_payment_amount("1803");
        data2List.setEncashOrExcess_amount("");
        data2List.setRemarks("");
        detailList.add(data2List);

        InvoiceDetail data3List = new InvoiceDetail();
        data3List.setSerial_no("3");
        data3List.setCustomer_name("HAFIZ AUTOS");
        data3List.setCity("KARACHI");
        data3List.setTour("MALAYSIA");
        data3List.setBusiness_amount("10,933,000");
        data3List.setScheme_payment_amount("91,837");
        data3List.setEncashOrExcess_amount("");
        data3List.setRemarks("");
        detailList.add(data3List);

        
        data.setListOfDetail(detailList);
        new Invoice().createPDF(RESULT);

    }

    public void createPDF(String result) {
        try {
            Document document = new Document();
            document.setPageSize(PageSize.A4);
            document.setMargins(15, 15, 20, 20);
            PdfWriter.getInstance(document, new FileOutputStream(result));
            document.open();

            // Heading
            PdfPTable table = new PdfPTable(1);
            PdfPCell cell = new PdfPCell(new Paragraph("FOREIGN TOUR SCHEME-2017",
                    FontFactory.getFont(result, result, true, 15, Font.NORMAL, BaseColor.BLACK)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("DYNAMIC TOUR CONTRIBUTION INVOICE",
                    FontFactory.getFont(result, result, true, 10, Font.NORMAL, BaseColor.WHITE)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.BLACK);
            table.setWidthPercentage(160 / 1.6f);
            table.addCell(cell);

            // Firs table for date, region & invoice number
            PdfPTable table1 = new PdfPTable(2);
            table1.setWidths(new float[]{1, 1.2f});
            table1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table1.setWidthPercentage(160 / 8.23f);
            PdfPCell cell1 = new PdfPCell(new Phrase("Date", FontFactory.getFont(FontFactory.COURIER, 8)));
            cell1.setBorderColor(BaseColor.WHITE);
            cell1.setBorderColorTop(BaseColor.BLACK);
            PdfPCell cell1_2 = new PdfPCell(new Phrase(data.getDate(), FontFactory.getFont(FontFactory.COURIER, 8)));
            cell1_2.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell cell1_3 = new PdfPCell(new Phrase("Invoice #", FontFactory.getFont(FontFactory.COURIER, 8)));
            cell1_3.setBorderColor(BaseColor.WHITE);
            PdfPCell cell1_4 = new PdfPCell(new Phrase(data.getInvoice_num(), FontFactory.getFont(FontFactory.COURIER, 8)));
            cell1_4.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell cell1_5 = new PdfPCell(new Phrase("Region", FontFactory.getFont(FontFactory.COURIER, 8)));
            cell1_5.setBorderColor(BaseColor.WHITE);
            PdfPCell cell1_6 = new PdfPCell(new Phrase(data.getRegion(), FontFactory.getFont(FontFactory.COURIER, 8)));
            cell1_6.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(cell1);
            table1.addCell(cell1_2);
            table1.addCell(cell1_3);
            table1.addCell(cell1_4);
            table1.addCell(cell1_5);
            table1.addCell(cell1_6);

            // Table for customer info
            PdfPTable table2 = new PdfPTable(1);
            table2.setHorizontalAlignment(Element.ALIGN_LEFT);
            table2.setWidthPercentage(160 / 6.5f);
            PdfPCell cell2 = new PdfPCell(new Phrase("INVOICE TO", FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.WHITE)));
            cell2.setBackgroundColor(BaseColor.BLACK);
            table2.addCell(cell2);
            table2.addCell(new Phrase(data.getInvoice_to(), FontFactory.getFont(FontFactory.COURIER, 8)));

            // Table for records
            PdfPTable table3 = new PdfPTable(8);
            table3.setWidths(new int[]{1, 4, 2, 2, 2, 2, 2, 2});
            table3.setWidthPercentage(160 / 1.6f);

            headerCell(table3, "S NO");
            headerCell(table3, "CUSTOMER NAME");
            headerCell(table3, "CITY");
            headerCell(table3, "TOUR");
            headerCell(table3, "BUSINESS AMOUNT");
            headerCell(table3, "SCHEME PAYMENT AMOUNT");
            headerCell(table3, "ENCASH/EXCESS AMOUNT");
            headerCell(table3, "REMARKS");

            for (InvoiceDetail obj : data.getListOfDetail()) {
                
                fieldCell(table3, obj.getSerial_no(), "center");
                fieldCell(table3, obj.getCustomer_name(), "left");
                fieldCell(table3, obj.getCity(), "center");
                fieldCell(table3, obj.getTour(), "center");
                fieldCell(table3, obj.getBusiness_amount(), "right");
                fieldCell(table3, obj.getScheme_payment_amount(), "right");
                fieldCell(table3, obj.getEncashOrExcess_amount(), "right");
                fieldCell(table3, obj.getRemarks(), "center");
            }


            PdfPCell cell3 = new PdfPCell(new Phrase("TOTAL AMOUNT", FontFactory.getFont(FontFactory.COURIER, 8)));
            cell3.setColspan(4);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            table3.addCell(cell3);
            fieldCell(table3, "24,583,816", "right");
            fieldCell(table3, "290,488", "right");
            fieldCell(table3, "73,150", "right");
            fieldCell(table3, "", result);

            document.add(table);
            document.add(table1);
            document.add(table2);
            document.add(table3);
            document.add(new Paragraph("", FontFactory.getFont(FontFactory.COURIER, 1)));

            document.close();

            JOptionPane.showMessageDialog(null, "Report saved!");
        } catch (FileNotFoundException | DocumentException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public PdfPCell headerCell(PdfPTable table, String value) {
        PdfPCell cell = new PdfPCell(new Phrase(value, FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.WHITE)));
        cell.setBackgroundColor(BaseColor.BLACK);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return table.addCell(cell);
    }

    public PdfPCell fieldCell(PdfPTable table, String value, String align) {
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

    public PdfPCell hiddenCell(PdfPTable table, String value) {
        PdfPCell cell = new PdfPCell(new Phrase(value, FontFactory.getFont(FontFactory.COURIER, 8)));
        cell.setBorderColor(BaseColor.WHITE);
        return table.addCell(cell);
    }
}
