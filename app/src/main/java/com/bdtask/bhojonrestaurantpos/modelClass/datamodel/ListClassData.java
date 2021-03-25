package com.bdtask.bhojonrestaurantpos.modelClass.datamodel;

public class ListClassData {
    private String ProductName, price, size, priceOfAddons, ProductsID, baseprice;
    private int quantitys;
    private int addons;
    private int addOnsTotal;
    private String productvat;
    private int quantity;
    private String variantid;

    public ListClassData() {
    }

    public ListClassData(String baseprice, String productName, String price, String size, String priceOfAddons, String productsID, int quantitys, int addons, int addOnsTotal,String productvat, int quantity, String variantid) {
        this.ProductName = productName;
        this.baseprice = baseprice;
        this.price = price;
        this.size = size;
        this.priceOfAddons = priceOfAddons;
        this.ProductsID = productsID;
        this.addons = addons;
        this.quantitys = quantitys;
        this.productvat = productvat;
        this.quantity = quantitys;
        this.variantid = variantid;
    }

    public int getAddons() {
        return addons;
    }

    public void setAddons(int addons) {
        this.addons = addons;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        this.ProductName = productName;
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

    public String getAddOnsTotal() {
        return priceOfAddons;
    }

    public void setAddOnsTotal(String addOnsTotal) {
        this.priceOfAddons = addOnsTotal;
    }

    public String getProductsID() {
        return ProductsID;
    }

    public void setProductsID(String productsID) {
        this.ProductsID = productsID;
    }


    public int getQuantity() {
        return quantitys;
    }

    public void setQuantity(int quantity) {
        this.quantitys = quantity;
    }

    public String getBaseprice() {
        return baseprice;
    }

    public void setBaseprice(String baseprice) {
        this.baseprice = baseprice;
    }

    public String getProductvat() {
        return productvat;
    }

    public void setProductvat(String productvat) {
        this.productvat = productvat;
    }

    public String getVariantid() {
        return variantid;
    }

    public void setVariantid(String variantid) {
        this.variantid = variantid;
    }
}
