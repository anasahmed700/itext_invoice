package com.itextpdf.first_invoice;

import java.util.ArrayList;

public class InvoiceData {

    private String invoice_to, date, invoice_num, region, 
            total_payable, totalAmount, totalEncash, totalExcess;
    
    
    private ArrayList<InvoiceDetail> listOfDetail;

    public ArrayList<InvoiceDetail> getListOfDetail() {
        return listOfDetail;
    }

    public void setListOfDetail(ArrayList<InvoiceDetail> listOfDetail) {
        this.listOfDetail = listOfDetail;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }


    public String getTotal_payable() {
        return total_payable;
    }

    public void setTotal_payable(String total_payable) {
        this.total_payable = total_payable;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalEncash() {
        return totalEncash;
    }

    public void setTotalEncash(String totalEncash) {
        this.totalEncash = totalEncash;
    }

    public String getTotalExcess() {
        return totalExcess;
    }

    public void setTotalExcess(String totalExcess) {
        this.totalExcess = totalExcess;
    }


   
}
