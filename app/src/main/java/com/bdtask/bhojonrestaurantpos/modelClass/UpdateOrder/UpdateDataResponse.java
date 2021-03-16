package com.bdtask.bhojonrestaurantpos.modelClass.UpdateOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateDataResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("updateOrderData")
    @Expose
    private UpdateOrderData updateOrderData;

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

    public UpdateOrderData getUpdateOrderData() {
        return updateOrderData;
    }

    public void setUpdateOrderData(UpdateOrderData updateOrderData) {
        this.updateOrderData = updateOrderData;
    }

}
