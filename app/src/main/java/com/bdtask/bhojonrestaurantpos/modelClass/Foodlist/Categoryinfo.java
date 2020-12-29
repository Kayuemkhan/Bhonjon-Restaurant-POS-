package com.bdtask.bhojonrestaurantpos.modelClass.Foodlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Categoryinfo {

    @SerializedName("CategoryID")
    @Expose
    private String categoryID;
    @SerializedName("Name")
    @Expose
    private String name;

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}