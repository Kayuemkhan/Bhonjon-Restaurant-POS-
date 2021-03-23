package com.bdtask.bhojonrestaurantpos.modelClass.UpdateOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpdateOrderData {
    @SerializedName("orderid")
    @Expose
    private String orderid;
    @SerializedName("Grandtotal")
    @Expose
    private String grandtotal;
    @SerializedName("Servicecharge")
    @Expose
    private String servicecharge;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("vat")
    @Expose
    private String vat;
    @SerializedName("Table")
    @Expose
    private String table;
    @SerializedName("customername")
    @Expose
    private String customername;
    @SerializedName("iteminfo")
    @Expose
    private List<Iteminfo> iteminfo = null;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getGrandtotal() {
        return grandtotal;
    }

    public void setGrandtotal(String grandtotal) {
        this.grandtotal = grandtotal;
    }

    public String getServicecharge() {
        return servicecharge;
    }

    public void setServicecharge(String servicecharge) {
        this.servicecharge = servicecharge;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public List<Iteminfo> getIteminfo() {
        return iteminfo;
    }

    public void setIteminfo(List<Iteminfo> iteminfo) {
        this.iteminfo = iteminfo;
    }

}