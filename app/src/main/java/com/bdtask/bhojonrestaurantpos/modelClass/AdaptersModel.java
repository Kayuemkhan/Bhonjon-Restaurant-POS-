package com.bdtask.bhojonrestaurantpos.modelClass;

public class AdaptersModel {
    private int position;
    private String paymentName;
    private String ammount;

    public AdaptersModel() {
    }

    public AdaptersModel(int position, String paymentName, String ammount) {
        this.position = position;
        this.paymentName = paymentName;
        this.ammount = ammount;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getAmmount() {
        return ammount;
    }

    public void setAmmount(String ammount) {
        this.ammount = ammount;
    }
}
