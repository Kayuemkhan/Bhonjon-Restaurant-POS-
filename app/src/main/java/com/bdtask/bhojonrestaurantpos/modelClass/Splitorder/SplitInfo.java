package com.bdtask.bhojonrestaurantpos.modelClass.Splitorder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SplitInfo {
    @SerializedName("iteminfo")
    @Expose
    private List<SplitData> iteminfo = null;

    public List<SplitData> getIteminfo() {
        return iteminfo;
    }

    public void setIteminfo(List<SplitData> iteminfo) {
        this.iteminfo = iteminfo;
    }
}
