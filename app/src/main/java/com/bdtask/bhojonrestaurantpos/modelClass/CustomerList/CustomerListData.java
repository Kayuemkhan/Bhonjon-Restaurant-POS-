package com.bdtask.bhojonrestaurantpos.modelClass.CustomerList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerListData {

    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("CustomerName")
    @Expose
    private String customerName;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
