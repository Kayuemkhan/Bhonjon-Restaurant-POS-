package com.bdtask.bhojonrestaurantpos.modelClass.KithcenStatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KitchenStatusData {
    @SerializedName("Table")
    @Expose
    private String table;
    @SerializedName("waiter")
    @Expose
    private String waiter;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("orderid")
    @Expose
    private String orderid;
    @SerializedName("customername")
    @Expose
    private String customername;
    @SerializedName("iteminfo")
    @Expose
    private List<Iteminfo> iteminfo = null;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getWaiter() {
        return waiter;
    }

    public void setWaiter(String waiter) {
        this.waiter = waiter;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
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
