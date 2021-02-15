package com.bdtask.bhojonrestaurantpos.modelClass.BankList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class BankListData {
    @SerializedName("bankid")
    @Expose
    private String bankid;
    @SerializedName("bankname")
    @Expose
    private String bankname;

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

}
