package com.itextpdf.ntwrktourinvoice;


public class NTInvoiceDetail {
    String serial_no, customer_name, city, business_amount, scheme_payment_amount, remarks; 

    
    public String getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBusiness_amount() {
        return business_amount;
    }

    public void setBusiness_amount(String business_amount) {
        this.business_amount = business_amount;
    }

    public String getScheme_payment_amount() {
        return scheme_payment_amount;
    }

    public void setScheme_payment_amount(String scheme_payment_amount) {
        this.scheme_payment_amount = scheme_payment_amount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    
}
