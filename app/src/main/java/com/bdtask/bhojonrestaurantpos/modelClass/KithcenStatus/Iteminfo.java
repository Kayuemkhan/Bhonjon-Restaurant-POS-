package com.bdtask.bhojonrestaurantpos.modelClass.KithcenStatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Iteminfo {
    @SerializedName("itemname")
    @Expose
    private String itemname;
    @SerializedName("varient")
    @Expose
    private String varient;
    @SerializedName("qty")
    @Expose
    private String qty;
    @SerializedName("acepttime")
    @Expose
    private String acepttime;
    @SerializedName("status")
    @Expose
    private String status;

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getVarient() {
        return varient;
    }

    public void setVarient(String varient) {
        this.varient = varient;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getAcepttime() {
        return acepttime;
    }

    public void setAcepttime(String acepttime) {
        this.acepttime = acepttime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
