package com.bdtask.bhojonrestaurantpos.modelClass.Splitordernum;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SplitordernumData {
    @SerializedName("orderid")
    @Expose
    private String orderid;
    @SerializedName("splitid")
    @Expose
    private Integer splitid;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public Integer getSplitid() {
        return splitid;
    }

    public void setSplitid(Integer splitid) {
        this.splitid = splitid;
    }
}
