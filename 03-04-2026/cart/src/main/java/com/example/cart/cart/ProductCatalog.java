package com.example.cart.cart;

public class ProductCatalog {

    private Long pid;
    private String pname;
    private String pcategory;
    private double discountedPrice;
    private int noOfItems;

    public ProductCatalog() {
    }

    public ProductCatalog(Long pid, String pname, String pcategory, double discountedPrice, int noOfItems) {
        this.pid = pid;
        this.pname = pname;
        this.pcategory = pcategory;
        this.discountedPrice = discountedPrice;
        this.noOfItems = noOfItems;
    }

    public Long getPid() {
        return pid;
    }

    public String getPname() {
        return pname;
    }

    public String getPcategory() {
        return pcategory;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public int getNoOfItems() {
        return noOfItems;
    }
}
