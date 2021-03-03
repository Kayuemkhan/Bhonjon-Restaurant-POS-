package com.bdtask.bhojonrestaurantpos.modelClass;

public class AdaptersModel {
    private int adapterPosition;
    private String paymentName;
    private String amount;
    private String payment_type_id;
    public AdaptersModel() {
    }

    public AdaptersModel(int adapterPosition, String paymentName, String amount, String payment_type_id) {
        this.adapterPosition = adapterPosition;
        this.paymentName = paymentName;
        this.amount = amount;
        this.payment_type_id = payment_type_id;
    }

    public int getAdapterPosition() {
        return adapterPosition;
    }

    public void setAdapterPosition(int adapterPosition) {
        this.adapterPosition = adapterPosition;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPayment_type_id() {
        return payment_type_id;
    }

    public void setPayment_type_id(String payment_type_id) {
        this.payment_type_id = payment_type_id;
    }
}
