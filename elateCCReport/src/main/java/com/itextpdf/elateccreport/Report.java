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
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
//import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        data.setHead_office("Head office:Suite No. 1,2,3, ST-3,Block-3,Gulshan-e-Iqbal,Karachi. ");
        data.setHO_phone("+92-21-34817330");
        data.setFax("+92-21-34813768");
        data.setEmail("Info@elategroup.com");
        data.setWeb("www.elategroup.com");
        data.setBranch_office("Branch office : Suite No. 01 2nd floor Royal Plaza 6th Road Satellite Town, Rawalpindi.");
        data.setBO_phone("+92-51-4848407");
        new Report().createPdf(RESULT);
    }
    
    public void createPdf(String result) throws BadElementException, IOException{
        try {
            Document document = new Document();
            document.setPageSize(PageSize.A4);
            document.setMargins(15, 15, 40, 80);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(result));
            HeaderTable event = new HeaderTable();
            
            writer.setPageEvent(event);
            
            
            document.open();
            
            
            // Customer info and date table
            PdfPTable custDateTable = new PdfPTable(2);
            borderLessCell(custDateTable, "To,", 'L');
            borderLessCell(custDateTable, "Date", 'R');
            borderLessCell(custDateTable, data.getCust_selection(), 'L');
            borderLessCell(custDateTable, data.getDate(), 'R');
            custDateTable.setWidthPercentage(100);
            
            // Product info
            Paragraph para1 = new Paragraph("Product Code  : ");
            para1.add(new Phrase(data.getProduct_code()));
            para1.add(Chunk.NEWLINE);
            para1.add("Product Name : ");
            para1.add(new Phrase(data.getProduct_name()));
                      
            // Subheading
            PdfPTable table1 = new PdfPTable(1);
            PdfPCell cell = new PdfPCell(new Phrase("Quotation", FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, BaseColor.BLUE)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(PdfPCell.BOTTOM);
            cell.setBorderColor(BaseColor.RED);
            table1.setWidthPercentage(15);
            table1.addCell(cell);
            // setting image file
            Image image = Image.getInstance("resources\\mehran.jpg");
            image.scaleAbsolute(180, 150);
            image.setAlignment(Element.ALIGN_CENTER);
            
            // HTML string
            String htmlString = "<P CLASS=\"MSONORMAL\" STYLE=\"TEXT-ALIGN:JUSTIFY\"><B><U><SPAN STYLE=\"FONT-SIZE: 12PT; LINE-HEIGHT: 115%;\">ANESTHESIA MACHINE WITH BUILT IN VENTILATOR AND MECHANICAL 03<O:P></O:P></SPAN></U></B></P>\n" +
"\n" +
"<P CLASS=\"MSONORMAL\" STYLE=\"TEXT-ALIGN:JUSTIFY\"><B><U><SPAN STYLE=\"FONT-SIZE: 12PT; LINE-HEIGHT: 115%;\">FLOWMETER WITH 02 VAPORIZERS (ISOFLURANE &AMP; SEVOFLURANE)<O:P></O:P></SPAN></U></B></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\"><SPAN STYLE=\"FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;;\n" +
"BACKGROUND:WHITE\">THE <B>MORPHEUS M</B> IS\n" +
"A WORKSTATION FOR GASEOUS ANESTHESIA AND IT CAN BE USED ON ADULT, CHILDREN AND\n" +
"NEWBORN PATIENTS. THE MORPHEUS M IS SUITABLE FOR ADMINISTRATION OF OXYGEN – AIR\n" +
"– NITROUS OXIDE – HALOTHANE – ENFLURANE – ISOFLURANE – SEVOFLURANE – DESFLURANE\n" +
"MIXTURES.</SPAN><SPAN STYLE=\"FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;\"><BR>\n" +
"<SPAN STYLE=\"BACKGROUND:WHITE\">THE UNIT FOR ANESTHESIA MORPHEUS M IS COMPLETED\n" +
"WITH:</SPAN><BR>\n" +
"<SPAN STYLE=\"BACKGROUND:WHITE\">&NBSP;ELECTRONIC LUNG VENTILATOR WITH <B>12” TFT COLOR SCREEN DISPLAY</B></SPAN></SPAN><SPAN CLASS=\"APPLE-CONVERTED-SPACE\"><SPAN STYLE=\"FONT-SIZE: 12PT; FONT-FAMILY: CALIBRI, SANS-SERIF; BACKGROUND: WHITE;\">&NBSP;</SPAN></SPAN><SPAN STYLE=\"FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;\"><BR>\n" +
"<SPAN STYLE=\"BACKGROUND:WHITE\">&NBSP;VALVES\n" +
"GROUP: OPEN, SEMI-CLOSED, CLOSED, HEATED, WITH SODA LIME ABSORBER </SPAN><BR>\n" +
"<SPAN STYLE=\"BACKGROUND:WHITE\">&NBSP;SIARE<ST1:STOCKTICKER W:ST=\"ON\">TEX</ST1:STOCKTICKER> RAPID CONNECTION DEVICE, SELECTATEC COMPATIBLE\n" +
"FOR 2 VAPORIZERS</SPAN><BR>\n" +
"<SPAN STYLE=\"BACKGROUND:WHITE\">&NBSP;GAS\n" +
"SUPPLY GROUP</SPAN></SPAN><SPAN CLASS=\"APPLE-CONVERTED-SPACE\"><SPAN STYLE=\"FONT-SIZE: 12PT; FONT-FAMILY: CALIBRI, SANS-SERIF; BACKGROUND: WHITE;\">&NBSP;<O:P></O:P></SPAN></SPAN></P>\n" +
"\n" +
"<P CLASS=\"MSONORMAL\"><SPAN CLASS=\"APPLE-CONVERTED-SPACE\"><SPAN STYLE=\"FONT-SIZE: 12PT; LINE-HEIGHT: 115%; BACKGROUND: WHITE;\">CYLINDER\n" +
"YOKE<O:P></O:P></SPAN></SPAN></P>\n" +
"\n" +
"<P CLASS=\"MSONORMAL\"><SPAN CLASS=\"APPLE-CONVERTED-SPACE\"><B><U><SPAN STYLE=\"FONT-SIZE: 12PT; LINE-HEIGHT: 115%; BACKGROUND: WHITE;\">LUNG VENTILATOR:<O:P></O:P></SPAN></U></B></SPAN></P>\n" +
"\n" +
"<P CLASS=\"MSONORMAL\"><SPAN STYLE=\"FONT-SIZE: 12PT; LINE-HEIGHT: 115%; BACKGROUND: WHITE;\">THE LUNG VENTILATOR INCORPORATES A <B>12” TFT HIGH</B> RESOLUTION COLOR DISPLAY\n" +
"SCREEN&NBSP; WITH &NBSP;SPIROMETRY MEMBRANE KEYBOARD AND ENCODER.</SPAN><SPAN STYLE=\"FONT-SIZE: 12PT; LINE-HEIGHT: 115%;\"><BR>\n" +
"<STRONG><SPAN STYLE=\"FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;;MSO-BIDI-FONT-FAMILY:\n" +
"ARIAL;BACKGROUND:WHITE\">VENTILATION MODALITIES:</SPAN></STRONG><BR>\n" +
"<SPAN STYLE=\"BACKGROUND:WHITE\">MANUAL\n" +
",VC-VAC,APCV,SPON,SIMV+PS(VOLUMETRIC),PSV,APNEA,BACK-UP</SPAN> <BR>\n" +
"<STRONG><SPAN STYLE=\"FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;;MSO-BIDI-FONT-FAMILY:\n" +
"ARIAL;BACKGROUND:WHITE\">MEASURED PARAMETERS:</SPAN></STRONG><BR>\n" +
"<SPAN STYLE=\"BACKGROUND:WHITE\">PAW; PEEP; VTE; EXPMV; RATE; I:E; FIO2</SPAN><BR>\n" +
"<SPAN STYLE=\"BACKGROUND:WHITE\">PMAX; PMEAN; PAUSE; VTI; FLOWI; FLOWE; TINSP;\n" +
"TESP; TPAUSE; CS; RI</SPAN></SPAN></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\" STYLE=\"LINE-HEIGHT:150%\"><STRONG><SPAN STYLE=\"FONT-SIZE: 12PT; LINE-HEIGHT: 150%; FONT-FAMILY: CALIBRI, SANS-SERIF; BACKGROUND: WHITE;\"><O:P>&NBSP;</O:P></SPAN></STRONG></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\" STYLE=\"LINE-HEIGHT:150%\"><STRONG><SPAN STYLE=\"FONT-SIZE: 12PT; LINE-HEIGHT: 150%; FONT-FAMILY: CALIBRI, SANS-SERIF; BACKGROUND: WHITE;\"><O:P>&NBSP;</O:P></SPAN></STRONG></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\" STYLE=\"LINE-HEIGHT:150%\"><STRONG><SPAN STYLE=\"FONT-SIZE: 12PT; LINE-HEIGHT: 150%; FONT-FAMILY: CALIBRI, SANS-SERIF; BACKGROUND: WHITE;\"><O:P>&NBSP;</O:P></SPAN></STRONG></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\" STYLE=\"LINE-HEIGHT:150%\"><STRONG><SPAN STYLE=\"FONT-SIZE: 12PT; LINE-HEIGHT: 150%; FONT-FAMILY: CALIBRI, SANS-SERIF; BACKGROUND: WHITE;\">DISPLAYED VENTILATION CURVES:</SPAN></STRONG><SPAN STYLE=\"FONT-SIZE: 12PT; LINE-HEIGHT: 150%; FONT-FAMILY: CALIBRI, SANS-SERIF;\"><BR>\n" +
"<SPAN STYLE=\"BACKGROUND:WHITE\">- PAW<SPAN CLASS=\"APPLE-CONVERTED-SPACE\">&NBSP;</SPAN></SPAN><BR>\n" +
"<SPAN STYLE=\"BACKGROUND:WHITE\">- FLOW</SPAN><BR>\n" +
"<SPAN STYLE=\"BACKGROUND:WHITE\">- VOLUME<SPAN CLASS=\"APPLE-CONVERTED-SPACE\">&NBSP;</SPAN></SPAN><BR>\n" +
"<SPAN STYLE=\"BACKGROUND:WHITE\">- LOOP (VOLUME/PAW ; VOLUME/FLOW)</SPAN><BR>\n" +
"<SPAN STYLE=\"BACKGROUND:WHITE\">READING IS MADE THROUGH AN EXCLUSIVE SIARE\n" +
"STÉRILIZABLE FLOW SENSOR OPERATING BY MAGNETIC INDUCTION AND POSITIONED INSIDE\n" +
"THE EXPIRATORY VALVE OF THE BREATHING SYSTEM. THIS TECHNOLOGICAL DEVELOPMENT\n" +
"ALLOWS ELIMINATING ALL MANAGEMENT COSTS RAISING FROM CONTINUOUS REPLACEMENT OFF\n" +
"LOW SENSORS POSITIONED DIRECTLY ON THE PATIENT’S MOUTH AND MAKES THE DEVICE UNIQUE\n" +
"IN ITS GENDER.</SPAN><BR>\n" +
"<STRONG><SPAN STYLE=\"FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;;MSO-BIDI-FONT-FAMILY:\n" +
"ARIAL;BACKGROUND:WHITE\">ADJUSTABLE PARAMETERS:</SPAN></STRONG><SPAN STYLE=\"BACKGROUND:WHITE\"><BR>\n" +
"</SPAN></SPAN><STRONG><SPAN STYLE=\"FONT-SIZE:12.0PT;LINE-HEIGHT:150%;\n" +
"FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;;FONT-WEIGHT:NORMAL;MSO-BIDI-FONT-WEIGHT:\n" +
"BOLD\">O2 BUILT-IN MIXER DEVICE FROM 21% TO 99%</SPAN></STRONG><SPAN STYLE=\"FONT-SIZE:12.0PT;LINE-HEIGHT:150%;FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;;\n" +
"MSO-BIDI-FONT-WEIGHT:BOLD\"><BR>\n" +
"<STRONG><SPAN STYLE=\"FONT-WEIGHT: NORMAL;\">I:E ADJUSTABLE RATIO: 1:1 – 1:1.5 – 1:2 – 1:3 – 2:1\n" +
"– 3:1</SPAN></STRONG><BR>\n" +
"<STRONG><SPAN STYLE=\"FONT-WEIGHT: NORMAL;\">TIDAL VOLUME : 5-1500 ML</SPAN></STRONG></SPAN><STRONG><SPAN STYLE=\"FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;;FONT-WEIGHT:NORMAL;MSO-BIDI-FONT-WEIGHT:\n" +
"BOLD\"><O:P></O:P></SPAN></STRONG></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\" STYLE=\"LINE-HEIGHT:150%\"><STRONG><SPAN STYLE=\"FONT-SIZE:\n" +
"12.0PT;LINE-HEIGHT:150%;FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;;FONT-WEIGHT:NORMAL;\n" +
"MSO-BIDI-FONT-WEIGHT:BOLD\">INSPIRATION FLOW: FROM 1 TO 80L/MIN<O:P></O:P></SPAN></STRONG></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\" STYLE=\"LINE-HEIGHT:150%\"><STRONG><SPAN STYLE=\"FONT-SIZE:\n" +
"12.0PT;LINE-HEIGHT:150%;FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;;FONT-WEIGHT:NORMAL;\n" +
"MSO-BIDI-FONT-WEIGHT:BOLD\">INSPIRATION TIME: 0.2 TO 5 SECONDS<O:P></O:P></SPAN></STRONG></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\" STYLE=\"LINE-HEIGHT:150%\"><STRONG><SPAN STYLE=\"FONT-SIZE:\n" +
"12.0PT;LINE-HEIGHT:150%;FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;;FONT-WEIGHT:NORMAL;\n" +
"MSO-BIDI-FONT-WEIGHT:BOLD\">PEEP OFF, 3 ÷ 30 CMH2O (STEP 1 CMH2O)<O:P></O:P></SPAN></STRONG></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\" STYLE=\"LINE-HEIGHT:150%\"><STRONG><SPAN STYLE=\"FONT-SIZE:\n" +
"12.0PT;LINE-HEIGHT:150%;FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;;FONT-WEIGHT:NORMAL;\n" +
"MSO-BIDI-FONT-WEIGHT:BOLD\">FLOW TRIGGER FROM OFF, 1 TO 15 L/MIN (STEP 1 L/MIN)<O:P></O:P></SPAN></STRONG></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\"><B><U><SPAN STYLE=\"FONT-SIZE: 12PT; FONT-FAMILY: CALIBRI, SANS-SERIF; BACKGROUND: WHITE;\">BREATHING SYSTEM:</SPAN></U></B><U><SPAN STYLE=\"FONT-SIZE: 12PT; FONT-FAMILY: CALIBRI, SANS-SERIF; BACKGROUND: WHITE;\"> </SPAN></U><SPAN STYLE=\"FONT-SIZE:12.0PT;\n" +
"FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;;MSO-BIDI-FONT-FAMILY:ARIAL;BACKGROUND:WHITE\">SYSTEM\n" +
"IS FULLY AUTOCLAVABLE<U> <O:P></O:P></U></SPAN></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\"><SPAN STYLE=\"FONT-SIZE: 12PT; FONT-FAMILY: CALIBRI, SANS-SERIF; BACKGROUND: WHITE;\">&NBSP;CO2 ABSORBER 1 KG<O:P></O:P></SPAN></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\"><SPAN STYLE=\"FONT-SIZE: 12PT; FONT-FAMILY: CALIBRI, SANS-SERIF; BACKGROUND: WHITE;\">CANISTER REPLACEMENT\n" +
"ALSO DURING INTERVENTIONS<O:P></O:P></SPAN></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\"><SPAN STYLE=\"FONT-SIZE: 12PT; FONT-FAMILY: CALIBRI, SANS-SERIF; BACKGROUND: WHITE;\">THE HEATED VALVE GROUP\n" +
"REDUCES CONDENSATION AND HEAT THE FRESH GAS.<O:P></O:P></SPAN></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\"><B><U><SPAN STYLE=\"FONT-SIZE: 12PT; FONT-FAMILY: CALIBRI, SANS-SERIF; BACKGROUND: WHITE;\">ALARMS:<O:P></O:P></SPAN></U></B></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\"><SPAN STYLE=\"FONT-SIZE: 12PT; FONT-FAMILY: CALIBRI, SANS-SERIF; BACKGROUND: WHITE;\">GAS FAILURE, HIGH\n" +
"&AMP; LOW AIRWAY PRESSURE, SATURATION S (FIO2), APNOEA, LOW BATTERY<O:P></O:P></SPAN></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\"><SPAN STYLE=\"FONT-SIZE: 12PT; FONT-FAMILY: CALIBRI, SANS-SERIF; BACKGROUND: WHITE;\">BATTERY BACKUP: 120\n" +
"MINUTES<O:P></O:P></SPAN></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\"><SPAN STYLE=\"FONT-SIZE: 12PT; FONT-FAMILY: CALIBRI, SANS-SERIF; BACKGROUND: WHITE;\">SCAVENGING SYSTEM\n" +
"PASSIVE<O:P></O:P></SPAN></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\"><B><U><SPAN STYLE=\"FONT-SIZE: 12PT; FONT-FAMILY: CALIBRI, SANS-SERIF; BACKGROUND: WHITE;\"><O:P><SPAN STYLE=\"TEXT-DECORATION-LINE: NONE;\">&NBSP;</SPAN></O:P></SPAN></U></B></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\"><B><U><SPAN STYLE=\"FONT-SIZE: 12PT; FONT-FAMILY: CALIBRI, SANS-SERIF; BACKGROUND: WHITE;\">STANDARD ACCESSORIES</SPAN></U></B><U><SPAN STYLE=\"FONT-SIZE: 12PT; FONT-FAMILY: CALIBRI, SANS-SERIF; BACKGROUND: WHITE;\"><O:P></O:P></SPAN></U></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\"><SPAN STYLE=\"FONT-SIZE:11.0PT;MSO-BIDI-FONT-SIZE:12.0PT;\n" +
"FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;\">SUPPLIED WITH 02 VAPORIZER<O:P></O:P></SPAN></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\"><SPAN STYLE=\"FONT-SIZE:11.0PT;MSO-BIDI-FONT-SIZE:12.0PT;\n" +
"FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;\">USER’S MANUAL<O:P></O:P></SPAN></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\"><SPAN STYLE=\"FONT-SIZE:11.0PT;MSO-BIDI-FONT-SIZE:12.0PT;\n" +
"FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;\">SERVICE MANUAL<O:P></O:P></SPAN></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\"><SPAN STYLE=\"FONT-SIZE:11.0PT;MSO-BIDI-FONT-SIZE:12.0PT;\n" +
"FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;\">O2 SUPPLY HOSE<O:P></O:P></SPAN></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\"><SPAN STYLE=\"FONT-SIZE:11.0PT;MSO-BIDI-FONT-SIZE:12.0PT;\n" +
"FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;\">N2O SUPPLY HOSE<O:P></O:P></SPAN></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\"><SPAN STYLE=\"FONT-SIZE:11.0PT;MSO-BIDI-FONT-SIZE:12.0PT;\n" +
"FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;\">AIR SUPPLY HOSE<O:P></O:P></SPAN></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\"><SPAN STYLE=\"FONT-SIZE:11.0PT;MSO-BIDI-FONT-SIZE:12.0PT;\n" +
"FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;\">TOP SPECIAL CO2 ABSORBER CANISTER OF 1 KG\n" +
"(NO.01 CANISTER WITH METAL COVER)<O:P></O:P></SPAN></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\"><SPAN STYLE=\"FONT-SIZE:11.0PT;MSO-BIDI-FONT-SIZE:12.0PT;\n" +
"FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;\">O2 CELL<O:P></O:P></SPAN></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\"><SPAN STYLE=\"FONT-SIZE:11.0PT;MSO-BIDI-FONT-SIZE:12.0PT;\n" +
"FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;\">MANUAL VENTILATION KIT<O:P></O:P></SPAN></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\"><SPAN STYLE=\"FONT-SIZE:11.0PT;MSO-BIDI-FONT-SIZE:12.0PT;\n" +
"FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;\">ELECTRIC POWER SUPPLY CABLE<O:P></O:P></SPAN></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\"><SPAN STYLE=\"FONT-SIZE:11.0PT;MSO-BIDI-FONT-SIZE:12.0PT;\n" +
"FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;\">BREATHING BAGS<O:P></O:P></SPAN></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\"><SPAN STYLE=\"FONT-SIZE:11.0PT;MSO-BIDI-FONT-SIZE:12.0PT;\n" +
"FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;\">RE-USEABLE SILICON AUTOCLAVE BREATHING\n" +
"CIRCUIT (ADULT, PEADS/ NEONATE)<O:P></O:P></SPAN></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\"><SPAN STYLE=\"FONT-SIZE:11.0PT;MSO-BIDI-FONT-SIZE:12.0PT;\n" +
"FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;\">MOUNT AND Y-PIECE<O:P></O:P></SPAN></P>\n" +
"\n" +
"<P CLASS=\"MSONOSPACING\"><SPAN STYLE=\"FONT-SIZE:11.0PT;MSO-BIDI-FONT-SIZE:12.0PT;\n" +
"FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;\">PIN INDEX CYLINDER YOKES FOR OXYGEN &AMP;\n" +
"N2O (ONE EACH AS BACKUP)<O:P></O:P></SPAN></P>\n" +
"\n" +
"<SPAN STYLE=\"FONT-SIZE:11.0PT;MSO-BIDI-FONT-SIZE:12.0PT;LINE-HEIGHT:115%;\n" +
"FONT-FAMILY:&QUOT;CALIBRI&QUOT;,&QUOT;SANS-SERIF&QUOT;;MSO-FAREAST-FONT-FAMILY:CALIBRI;MSO-BIDI-FONT-FAMILY:\n" +
"&QUOT;TIMES NEW ROMAN&QUOT;;MSO-ANSI-LANGUAGE:EN-US;MSO-FAREAST-LANGUAGE:EN-US;\n" +
"MSO-BIDI-LANGUAGE:AR-SA\">PIN INDEX CYLINDER (2X O2 AND 2 X N2O)</SPAN>";
            // Price tag
            Paragraph pricePara = new Paragraph("PRICE: ");
            pricePara.add(new Phrase(data.getPrice()));
            pricePara.setAlignment(Element.ALIGN_RIGHT);
            
//            document.add(table);
//            document.add(new ElateHeaderFooter().header());
            document.add(new Phrase(Chunk.NEWLINE));
            document.add(custDateTable);
            document.add(para1);
//            document.add(p);
            document.add(Chunk.NEWLINE);
            document.add(table1);
            document.add(image);
            htmlToString(document, htmlString);
            document.add(pricePara);
            
//            PdfContentByte canvas = writer.getDirectContent();
//            CMYKColor magentaColor = new CMYKColor(0.f, 1.f, 1.f, 0.f);
//            canvas.setColorStroke(magentaColor);
//            canvas.moveTo(document.left(), 80);
//            canvas.lineTo(document.right(), 80);
//            canvas.moveTo(document.left(), 80);
//            canvas.lineTo(document.right(), 80);
//            canvas.closePathStroke();
          
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
    // fuction for converting HTML to string
    public HTMLWorker htmlToString(Document document, String html_string) throws IOException{
        HTMLWorker htmlWorker = new HTMLWorker(document);
            //html parsing
            String htmlString = html_string;
            htmlWorker.parse(new StringReader(htmlString));
            return htmlWorker;
    }
    
    
    public class HeaderTable extends PdfPageEventHelper {
        protected PdfPTable header;
        protected PdfPTable footer;
        protected float tableHeight;
        public HeaderTable() throws IOException, BadElementException {
            header = new PdfPTable(1);
            header.setTotalWidth(565);
            header.setLockedWidth(true);
            Image image = Image.getInstance("resources\\elatelogo.png");
            image.scaleAbsolute(200, 200);
            PdfPCell cell = new PdfPCell();           
            cell.setBorder(PdfPCell.BOTTOM);
            cell.setBorderColor(BaseColor.RED);
            Paragraph para = new Paragraph();
            para.add(new Chunk(image,0,0));
            para.add(new Phrase("ELATE C.C (PVT) LTD.",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 22, Font.BOLDITALIC, BaseColor.BLUE)));

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
            tableHeight = header.getTotalHeight();
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
//            Phrase p = new Phrase();
            PdfContentByte canvas = writer.getDirectContent();
            CMYKColor magentaColor = new CMYKColor(0.f, 1.f, 1.f, 0.f);
            canvas.setColorStroke(magentaColor);
            canvas.moveTo(document.left(), 80);
            canvas.lineTo(document.right(), 80);
            canvas.moveTo(document.left(), 80);
            canvas.lineTo(document.right(), 80);
            canvas.closePathStroke();
            String p = data.getHead_office()+" | Phone:"+data.getHO_phone();
            String p1 = "Fax:"+data.getFax()+" | Email:"+data.getEmail()+" | Web:"+ data.getWeb();
            String p2 = data.getBranch_office()+" | Phone:"+data.getBO_phone();
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(p, FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, BaseColor.BLUE)), 300, 65, 0);
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(p1, FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, BaseColor.BLUE)), 300, 50, 0);
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(p2, FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, BaseColor.BLUE)), 300, 35, 0);

        }
    }  
}

