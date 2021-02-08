package com.bdtask.bhojonrestaurantpos.modelClass.SignupNewCustomer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignupNewCustomerResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private SignupNewCustomerData data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SignupNewCustomerData getData() {
        return data;
    }

    public void setData(SignupNewCustomerData data) {
        this.data = data;
    }
}
