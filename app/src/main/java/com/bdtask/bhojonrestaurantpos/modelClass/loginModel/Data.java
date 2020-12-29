package com.bdtask.bhojonrestaurantpos.modelClass.loginModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("UserPictureURL")
    @Expose
    private String userPictureURL;
    @SerializedName("PowerBy")
    @Expose
    private String powerBy;
    @SerializedName("currencysign")
    @Expose
    private String currencysign;
    @SerializedName("servicecharge")
    @Expose
    private String servicecharge;

    public String getCurrencysign() {
        return currencysign;
    }

    public String getServicecharge() {
        return servicecharge;
    }

    public void setCurrencysign(String currencysign) {
        this.currencysign = currencysign;
    }

    public String getPowerBy() {
        return powerBy;
    }

    public void setPowerBy(String powerBy) {
        this.powerBy = powerBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUserPictureURL() {
        return userPictureURL;
    }

    public void setUserPictureURL(String userPictureURL) {
        this.userPictureURL = userPictureURL;
    }
}
