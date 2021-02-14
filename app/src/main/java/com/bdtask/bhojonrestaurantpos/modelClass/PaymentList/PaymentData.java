package com.bdtask.bhojonrestaurantpos.modelClass.PaymentList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentData {

    @SerializedName("payid")
    @Expose
    private String payid;
    @SerializedName("payname")
    @Expose
    private String payname;

    public String getPayid() {
        return payid;
    }

    public void setPayid(String payid) {
        this.payid = payid;
    }

    public String getPayname() {
        return payname;
    }

    public void setPayname(String payname) {
        this.payname = payname;
    }
}
