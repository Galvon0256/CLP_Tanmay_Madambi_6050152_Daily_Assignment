package com.example.product.catalog;

public class ProductDetails {

    private Long pid;
    private String pname;
    private String pcategory;

    public ProductDetails(Long pid, String pname, String pcategory) {
        this.pid = pid;
        this.pname = pname;
        this.pcategory = pcategory;
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
}
