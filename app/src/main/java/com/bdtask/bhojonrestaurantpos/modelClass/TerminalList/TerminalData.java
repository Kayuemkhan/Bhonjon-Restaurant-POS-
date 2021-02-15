package com.bdtask.bhojonrestaurantpos.modelClass.TerminalList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TerminalData {
    @SerializedName("terminalid")
    @Expose
    private String terminalid;
    @SerializedName("terminalname")
    @Expose
    private String terminalname;

    public String getTerminalid() {
        return terminalid;
    }

    public void setTerminalid(String terminalid) {
        this.terminalid = terminalid;
    }

    public String getTerminalname() {
        return terminalname;
    }

    public void setTerminalname(String terminalname) {
        this.terminalname = terminalname;
    }

}
