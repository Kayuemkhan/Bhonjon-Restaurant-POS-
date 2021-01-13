package com.bdtask.bhojonrestaurantpos.modelClass.WaiterList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WaiterlistData {
    @SerializedName("waiterid")
    @Expose
    private String waiterid;
    @SerializedName("Waitername")
    @Expose
    private String waitername;

    public String getWaiterid() {
        return waiterid;
    }

    public void setWaiterid(String waiterid) {
        this.waiterid = waiterid;
    }

    public String getWaitername() {
        return waitername;
    }

    public void setWaitername(String waitername) {
        this.waitername = waitername;
    }
}
