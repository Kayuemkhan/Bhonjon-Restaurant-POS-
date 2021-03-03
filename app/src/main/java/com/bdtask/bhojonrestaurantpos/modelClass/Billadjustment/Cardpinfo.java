package com.bdtask.bhojonrestaurantpos.modelClass.Billadjustment;

public class Cardpinfo {
    private String card_no;
    private String terminal_name;
    private String Bank;

    public Cardpinfo(String card_no, String terminal_name, String bank) {
        this.card_no = card_no;
        this.terminal_name = terminal_name;
        Bank = bank;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getTerminal_name() {
        return terminal_name;
    }

    public void setTerminal_name(String terminal_name) {
        this.terminal_name = terminal_name;
    }

    public String getBank() {
        return Bank;
    }

    public void setBank(String bank) {
        Bank = bank;
    }
}
