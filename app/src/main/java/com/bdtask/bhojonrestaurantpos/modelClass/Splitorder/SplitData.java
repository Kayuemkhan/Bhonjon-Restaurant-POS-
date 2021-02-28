package com.bdtask.bhojonrestaurantpos.modelClass.Splitorder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SplitData {
    @SerializedName("orderid")
    @Expose
    private String orderid;
    @SerializedName("menuid")
    @Expose
    private String menuid;
    @SerializedName("ProductName")
    @Expose
    private String productName;
    @SerializedName("Varientname")
    @Expose
    private String varientname;
    @SerializedName("Varientid")
    @Expose
    private String varientid;
    @SerializedName("Itemqty")
    @Expose
    private String itemqty;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("addons")
    @Expose
    private Integer addons;
    @SerializedName("addonsinfo")
    @Expose
    private List<SplitAddons> addonsinfo = null;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getVarientname() {
        return varientname;
    }

    public void setVarientname(String varientname) {
        this.varientname = varientname;
    }

    public String getVarientid() {
        return varientid;
    }

    public void setVarientid(String varientid) {
        this.varientid = varientid;
    }

    public String getItemqty() {
        return itemqty;
    }

    public void setItemqty(String itemqty) {
        this.itemqty = itemqty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getAddons() {
        return addons;
    }

    public void setAddons(Integer addons) {
        this.addons = addons;
    }

    public List<SplitAddons> getAddonsinfo() {
        return addonsinfo;
    }

    public void setAddonsinfo(List<SplitAddons> addonsinfo) {
        this.addonsinfo = addonsinfo;
    }
}
