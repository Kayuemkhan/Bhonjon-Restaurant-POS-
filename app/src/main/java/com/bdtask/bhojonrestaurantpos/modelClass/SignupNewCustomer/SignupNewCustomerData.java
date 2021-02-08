package com.bdtask.bhojonrestaurantpos.modelClass.SignupNewCustomer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignupNewCustomerData {
    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("cuntomer_no")
    @Expose
    private String cuntomerNo;
    @SerializedName("membership_type")
    @Expose
    private String membershipType;
    @SerializedName("customer_name")
    @Expose
    private String customerName;
    @SerializedName("customer_email")
    @Expose
    private String customerEmail;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("customer_token")
    @Expose
    private String customerToken;
    @SerializedName("customer_address")
    @Expose
    private String customerAddress;
    @SerializedName("customer_phone")
    @Expose
    private String customerPhone;
    @SerializedName("customer_picture")
    @Expose
    private String customerPicture;
    @SerializedName("favorite_delivery_address")
    @Expose
    private String favoriteDeliveryAddress;
    @SerializedName("crdate")
    @Expose
    private String crdate;
    @SerializedName("is_active")
    @Expose
    private String isActive;
    @SerializedName("UserPictureURL")
    @Expose
    private String userPictureURL;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCuntomerNo() {
        return cuntomerNo;
    }

    public void setCuntomerNo(String cuntomerNo) {
        this.cuntomerNo = cuntomerNo;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCustomerToken() {
        return customerToken;
    }

    public void setCustomerToken(String customerToken) {
        this.customerToken = customerToken;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerPicture() {
        return customerPicture;
    }

    public void setCustomerPicture(String customerPicture) {
        this.customerPicture = customerPicture;
    }

    public String getFavoriteDeliveryAddress() {
        return favoriteDeliveryAddress;
    }

    public void setFavoriteDeliveryAddress(String favoriteDeliveryAddress) {
        this.favoriteDeliveryAddress = favoriteDeliveryAddress;
    }

    public String getCrdate() {
        return crdate;
    }

    public void setCrdate(String crdate) {
        this.crdate = crdate;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getUserPictureURL() {
        return userPictureURL;
    }

    public void setUserPictureURL(String userPictureURL) {
        this.userPictureURL = userPictureURL;
    }

}
