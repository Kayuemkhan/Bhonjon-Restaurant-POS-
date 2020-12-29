package com.bdtask.bhojonrestaurantpos.modelClass.Allcategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllCategoriesData {
    @SerializedName("PcategoryID")
    @Expose
    private String pcategoryID;
    @SerializedName("Restaurantvat")
    @Expose
    private String restaurantvat;
    @SerializedName("foodinfo")
    @Expose
    private List<Foodinfo> foodinfo = null;

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

    public List<Foodinfo> getFoodinfo() {
        return foodinfo;
    }

    public void setFoodinfo(List<Foodinfo> foodinfo) {
        this.foodinfo = foodinfo;
    }

}