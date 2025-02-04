package com.bdtask.bhojonrestaurantpos.modelClass.Billadjustment;

import java.util.List;

public class PaymentInfo {
    private String payment_type_id;
    private String amount;
    private List<Cardpinfo> cardpinfo;

    public PaymentInfo(String payment_type_id, String amount, List<Cardpinfo> Cardpinfo) {
        this.payment_type_id = payment_type_id;
        this.amount = amount;
        this.cardpinfo = Cardpinfo;
    }


    public PaymentInfo() {
    }

    public String getPayment_type_id() {
        return payment_type_id;
    }

    public void setPayment_type_id(String payment_type_id) {
        this.payment_type_id = payment_type_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public List<Cardpinfo> getCardpinfo() {
        return cardpinfo;
    }

    public void setCardpinfo(List<Cardpinfo> Cardpinfos) {
        this.cardpinfo = Cardpinfos;
    }
}
