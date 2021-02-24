package com.bdtask.bhojonrestaurantpos.BillAdjustment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillAdjustmentResponse {
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
    private BillAdjustMentData data;

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

    public BillAdjustMentData getData() {
        return data;
    }

    public void setData(BillAdjustMentData data) {
        this.data = data;
    }
}
