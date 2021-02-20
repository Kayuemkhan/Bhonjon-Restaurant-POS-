package com.bdtask.bhojonrestaurantpos.modelClass.PlaceOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaceOrderData {
    @SerializedName("orderid")
    @Expose
    private Integer orderid;

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }
}
