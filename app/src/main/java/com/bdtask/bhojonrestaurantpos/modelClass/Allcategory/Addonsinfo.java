package com.bdtask.bhojonrestaurantpos.modelClass.Allcategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Addonsinfo {

    @SerializedName("addonsid")
    @Expose
    private String addonsid;
    @SerializedName("add_on_name")
    @Expose
    private String addOnName;
    @SerializedName("addonsprice")
    @Expose
    private String addonsprice;

    public String getAddonsid() {
        return addonsid;
    }

    public void setAddonsid(String addonsid) {
        this.addonsid = addonsid;
    }

    public String getAddOnName() {
        return addOnName;
    }

    public void setAddOnName(String addOnName) {
        this.addOnName = addOnName;
    }

    public String getAddonsprice() {
        return addonsprice;
    }

    public void setAddonsprice(String addonsprice) {
        this.addonsprice = addonsprice;
    }

}
