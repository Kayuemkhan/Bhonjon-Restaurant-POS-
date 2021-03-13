package com.bdtask.bhojonrestaurantpos.modelClass.TableListUpdate;
// Checking the double click for maintaing merge orders
public class TableDetails {
    String orderid,grandTotal;

    public TableDetails() {
    }

    public TableDetails(String orderid, String grandTotal) {
        this.orderid = orderid;
        this.grandTotal = grandTotal;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }
}
