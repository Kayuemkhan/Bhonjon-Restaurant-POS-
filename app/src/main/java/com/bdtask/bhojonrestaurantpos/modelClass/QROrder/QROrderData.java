package com.bdtask.bhojonrestaurantpos.modelClass.QROrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QROrderData {
    @SerializedName("orderid")
    @Expose
    private String orderid;
    @SerializedName("CustomerName")
    @Expose
    private String customerName;
    @SerializedName("CustomerType")
    @Expose
    private String customerType;
    @SerializedName("waiter")
    @Expose
    private String waiter;
    @SerializedName("tablename")
    @Expose
    private String tablename;
    @SerializedName("OrderDate")
    @Expose
    private String orderDate;
    @SerializedName("totalamount")
    @Expose
    private String totalamount;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getWaiter() {
        return waiter;
    }

    public void setWaiter(String waiter) {
        this.waiter = waiter;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }
}
