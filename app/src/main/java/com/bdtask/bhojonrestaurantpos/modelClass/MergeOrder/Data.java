package com.bdtask.bhojonrestaurantpos.modelClass.MergeOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("marge_orderid")
    @Expose
    private String margeOrderid;

    public String getMargeOrderid() {
        return margeOrderid;
    }

    public void setMargeOrderid(String margeOrderid) {
        this.margeOrderid = margeOrderid;
    }

}