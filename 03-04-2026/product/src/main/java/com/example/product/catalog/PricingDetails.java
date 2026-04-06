package com.example.product.catalog;

public class PricingDetails {

    private Long pid;
    private double price;
    private double discount;

    public PricingDetails(Long pid, double price, double discount) {
        this.pid = pid;
        this.price = price;
        this.discount = discount;
    }

    public Long getPid() {
        return pid;
    }

    public double discountedPrice() {
        return Math.max(0, price - discount);
    }
}
