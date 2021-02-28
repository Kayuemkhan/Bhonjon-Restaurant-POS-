package com.bdtask.bhojonrestaurantpos.modelClass.Splitorder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SplitAddons {
    @SerializedName("add_on_name")
    @Expose
    private String addOnName;
    @SerializedName("addonsid")
    @Expose
    private String addonsid;
    @SerializedName("addonsprice")
    @Expose
    private String addonsprice;
    @SerializedName("addonsquantity")
    @Expose
    private String addonsquantity;

    public String getAddOnName() {
        return addOnName;
    }

    public void setAddOnName(String addOnName) {
        this.addOnName = addOnName;
    }

    public String getAddonsid() {
        return addonsid;
    }

    public void setAddonsid(String addonsid) {
        this.addonsid = addonsid;
    }

    public String getAddonsprice() {
        return addonsprice;
    }

    public void setAddonsprice(String addonsprice) {
        this.addonsprice = addonsprice;
    }

    public String getAddonsquantity() {
        return addonsquantity;
    }

    public void setAddonsquantity(String addonsquantity) {
        this.addonsquantity = addonsquantity;
    }
}
