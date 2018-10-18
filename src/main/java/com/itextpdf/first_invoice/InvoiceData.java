package com.itextpdf.first_invoice;

public class InvoiceData {

    private String invoice_to, date, invoice_num, region, serial_no, customer_name, city, tour, remarks,
            total_payable, business_amount, scheme_payment_amount, encashOrExcess_amount;
//    private int total_payable, business_amount, scheme_payment_amount, encashOrExcess_amount;

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

    public String getTour() {
        return tour;
    }

    public void setTour(String tour) {
        this.tour = tour;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTotal_payable() {
        return total_payable;
    }

    public void setTotal_payable(String total_payable) {
        this.total_payable = total_payable;
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

    public String getEncashOrExcess_amount() {
        return encashOrExcess_amount;
    }

    public void setEncashOrExcess_amount(String encashOrExcess_amount) {
        this.encashOrExcess_amount = encashOrExcess_amount;
    }

   
}
