package com.bdtask.bhojonrestaurantpos.modelClass.Foodlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("PcategoryID")
    @Expose
    private String pcategoryID;
    @SerializedName("Restaurantvat")
    @Expose
    private String restaurantvat;
    @SerializedName("categoryinfo")
    @Expose
    private List<Categoryinfo> categoryinfo = null;
    @SerializedName("foodinfo")
    @Expose
    private List<FoodinfoFoodList> foodinfo = null;

    public String getPcategoryID() {
        return pcategoryID;
    }

    public void setPcategoryID(String pcategoryID) {
        this.pcategoryID = pcategoryID;
    }

    public String getRestaurantvat() {
        return restaurantvat;
    }

    public void setRestaurantvat(String restaurantvat) {
        this.restaurantvat = restaurantvat;
    }

    public List<Categoryinfo> getCategoryinfo() {
        return categoryinfo;
    }

    public void setCategoryinfo(List<Categoryinfo> categoryinfo) {
        this.categoryinfo = categoryinfo;
    }

    public List<FoodinfoFoodList> getFoodinfo() {
        return foodinfo;
    }

    public void setFoodinfo(List<FoodinfoFoodList> foodinfo) {
        this.foodinfo = foodinfo;
    }

}