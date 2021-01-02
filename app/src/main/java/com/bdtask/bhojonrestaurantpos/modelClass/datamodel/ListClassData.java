package com.bdtask.bhojonrestaurantpos.modelClass.datamodel;

public class ListClassData {
    private String productname,price,size,t,productsID;
    private int quantity;


    public ListClassData() {
    }

    public ListClassData(String productname, String price, String size, String t, String productsID, int quantity) {
        this.productname = productname;
        this.price = price;
        this.size = size;
        this.t = t;
        this.productsID = productsID;
        this.quantity = quantity;
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

    public String getProductsID() {
        return productsID;
    }

    public void setProductsID(String productsID) {
        this.productsID = productsID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
