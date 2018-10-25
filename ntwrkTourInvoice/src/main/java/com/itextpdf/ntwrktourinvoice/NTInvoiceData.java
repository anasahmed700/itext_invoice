package com.itextpdf.ntwrktourinvoice;

import java.util.ArrayList;


public class NTInvoiceData {
    private String invoice_to, date, invoice_num, receivable;
    private ArrayList<NTInvoiceDetail> listOfDetails;

    public ArrayList<NTInvoiceDetail> getListOfDetails() {
        return listOfDetails;
    }

    public void setListOfDetails(ArrayList<NTInvoiceDetail> listOfDetails) {
        this.listOfDetails = listOfDetails;
    }
   
    
    public String getInvoice_to() {
        return invoice_to;
    }

    public void setInvoice_to(String invoice_to) {
        this.invoice_to = invoice_to;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInvoice_num() {
        return invoice_num;
    }

    public void setInvoice_num(String invoice_num) {
        this.invoice_num = invoice_num;
    }

    public String getReceivable() {
        return receivable;
    }

    public void setReceivable(String receivable) {
        this.receivable = receivable;
    }
    
    
}
    