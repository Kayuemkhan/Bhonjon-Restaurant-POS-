package com.bdtask.bhojonrestaurantpos.modelClass.Assignitemtosplitorder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssignItemToSplitorderItemInfo {
    @SerializedName("itemname")
    @Expose
    private String itemname;
    @SerializedName("varient")
    @Expose
    private String varient;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("qty")
    @Expose
    private Integer qty;
    @SerializedName("totalPrice")
    @Expose
    private Integer totalPrice;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

}
