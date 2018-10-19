package com.itextpdf.first_invoice;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
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

    public static final String RESULT = "invoice.pdf";

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
        data1List.setRemarks("EXCESS");
        detailList.add(data1List);

        InvoiceDetail data2List = new InvoiceDetail();

        data2List.setSerial_no("2");
        data2List.setCustomer_name("ASIA AUTOS");
        data2List.setCity("KARACHI");
        data2List.setTour("MALAYSIA");
        data2List.setBusiness_amount("214,700");
        data2List.setScheme_payment_amount("1,803");
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

        InvoiceDetail data4List = new InvoiceDetail();
        data4List.setSerial_no("4");
        data4List.setCustomer_name("OWAIS AUTOS");
        data4List.setCity("KARACHI");
        data4List.setTour("MALAYSIA");
        data4List.setBusiness_amount("6,196,116");
        data4List.setScheme_payment_amount("52,047");
        data4List.setEncashOrExcess_amount("350");
        data4List.setRemarks("EXCESS");
        detailList.add(data4List);
        
        InvoiceDetail data5List = new InvoiceDetail();
        data5List.setSerial_no("5");
        data5List.setCustomer_name("SUBHAN ALLAH AUTOS");
        data5List.setCity("KARACHI");
        data5List.setTour("THAILAND");
        data5List.setBusiness_amount("3,600,000");
        data5List.setScheme_payment_amount("72,000");
        data5List.setEncashOrExcess_amount("72,000");
        data5List.setRemarks("ENCASH");
        detailList.add(data5List);
        
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
                    FontFactory.getFont(result, result, true, 17, Font.NORMAL, BaseColor.BLACK)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("DYNAMIC TOUR CONTRIBUTION INVOICE",
                    FontFactory.getFont(result, result, true, 12, Font.NORMAL, BaseColor.WHITE)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.BLACK);
            table.setWidthPercentage(160 / 1.6f);
            table.addCell(cell);

            // Firs table for date, region & invoice number
            PdfPTable table1 = new PdfPTable(2);
            table1.setWidths(new float[]{1, 1.2f});
            table1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table1.setWidthPercentage(160 / 7.23f);
            borderLessCell(table1, "DATE", 'L');
            PdfPCell cell1_2 = new PdfPCell(new Phrase(data.getDate(), FontFactory.getFont(FontFactory.COURIER, 10)));
            cell1_2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(cell1_2);
            
            borderLessCell(table1, "INVOICE#", 'L');
            PdfPCell cell1_4 = new PdfPCell(new Phrase(data.getInvoice_num(), FontFactory.getFont(FontFactory.COURIER, 10)));
            cell1_4.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(cell1_4);
            
            borderLessCell(table1, "REGION", 'L');
            PdfPCell cell1_6 = new PdfPCell(new Phrase(data.getRegion(), FontFactory.getFont(FontFactory.COURIER, 10)));
            cell1_6.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(cell1_6);

            // Table for customer info
            PdfPTable table2 = new PdfPTable(1);
            table2.setHorizontalAlignment(Element.ALIGN_LEFT);
            table2.setWidthPercentage(160 / 6.5f);
            PdfPCell cell2 = new PdfPCell(new Phrase("INVOICE TO", FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.WHITE)));
            cell2.setBackgroundColor(BaseColor.BLACK);
            table2.addCell(cell2);
            table2.addCell(new Phrase(data.getInvoice_to(), FontFactory.getFont(FontFactory.COURIER, 10)));

            // Table for records
            PdfPTable table3 = new PdfPTable(8);
            table3.setWidths(new float[]{1, 3.5f, 2, 2, 2, 2, 2, 2});
            table3.setWidthPercentage(160 / 1.6f);
            // Table headers
            headerCell(table3, "S NO");
            headerCell(table3, "CUSTOMER NAME");
            headerCell(table3, "CITY");
            headerCell(table3, "TOUR");
            headerCell(table3, "BUSINESS AMOUNT");
            headerCell(table3, "SCHEME PAYMENT AMOUNT");
            headerCell(table3, "ENCASH/EXCESS AMOUNT");
            headerCell(table3, "REMARKS");
            
            // Table fields
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


            PdfPCell cell3 = new PdfPCell(new Phrase("TOTAL AMOUNT", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
            cell3.setColspan(4);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            table3.addCell(cell3);
            boldCell(table3, "24,583,816", "right");
            boldCell(table3, "290,488", "right");
            boldCell(table3, "73,150", "right");
            fieldCell(table3, "", result);
            cell3 = new PdfPCell(new Phrase("ENCASH AMOUNT", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
            cell3.setColspan(4);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            table3.addCell(cell3);
            fieldCell(table3, "", result);
            boldCell(table3, "72,000", "right");
            fieldCell(table3, "", result);
            fieldCell(table3, "", result);
            cell3 = new PdfPCell(new Phrase("EXCESS AMOUNT", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
            cell3.setColspan(4);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            table3.addCell(cell3);
            fieldCell(table3, "", result);
            boldCell(table3, "1,150", "right");
            fieldCell(table3, "", result);
            fieldCell(table3, "", result);
            cell3 = new PdfPCell(new Phrase("TOTAL PAYABLE", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
            cell3.setColspan(4);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            table3.addCell(cell3);
            fieldCell(table3, "", result);
            boldCell(table3, "217,338", "right");
            fieldCell(table3, "", result);
            fieldCell(table3, "", result);

            document.add(table);
            document.add(table1);
            document.add(table2);
            document.add(table3);
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

            document.close();

            JOptionPane.showMessageDialog(null, "Report saved!");
        } catch (FileNotFoundException | DocumentException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public PdfPCell headerCell(PdfPTable table, String value) {
        PdfPCell cell = new PdfPCell(new Phrase(value, FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.WHITE)));
        cell.setBackgroundColor(BaseColor.BLACK);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return table.addCell(cell);
    }

    public PdfPCell fieldCell(PdfPTable table, String value, String align) {
        PdfPCell cell = new PdfPCell(new Phrase(value, FontFactory.getFont(FontFactory.COURIER, 10)));
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

    public PdfPCell boldCell(PdfPTable table, String value, String align) {
        PdfPCell cell = new PdfPCell(new Phrase(value, FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
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
}
