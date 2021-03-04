package com.bdtask.bhojonrestaurantpos.modelClass.Assignitemtosplitorder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AssignItemToSplitorderData {
    @SerializedName("iteminfo")
    @Expose
    private List<AssignItemToSplitorderItemInfo> iteminfo = null;
    @SerializedName("Subtotal")
    @Expose
    private String subtotal;
    @SerializedName("VAT")
    @Expose
    private String vAT;
    @SerializedName("Servicecharge")
    @Expose
    private String servicecharge;
    @SerializedName("Grandtotal")
    @Expose
    private String grandtotal;

    public List<AssignItemToSplitorderItemInfo> getIteminfo() {
        return iteminfo;
    }

    public void setIteminfo(List<AssignItemToSplitorderItemInfo> iteminfo) {
        this.iteminfo = iteminfo;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getVAT() {
        return vAT;
    }

    public void setVAT(String vAT) {
        this.vAT = vAT;
    }

    public String getServicecharge() {
        return servicecharge;
    }

    public void setServicecharge(String servicecharge) {
        this.servicecharge = servicecharge;
    }

    public String getGrandtotal() {
        return grandtotal;
    }

    public void setGrandtotal(String grandtotal) {
        this.grandtotal = grandtotal;
    }
}
