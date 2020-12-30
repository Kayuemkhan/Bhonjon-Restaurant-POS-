package com.bdtask.bhojonrestaurantpos.modelClass.datamodel;

public class ListClassData {
    private String productname,price,size,t;


    public ListClassData() {
    }

    public ListClassData(String productname, String price, String size, String t) {
        this.productname = productname;
        this.price = price;
        this.size = size;
        this.t = t;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }
}
