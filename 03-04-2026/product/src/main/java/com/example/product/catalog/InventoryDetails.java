package com.example.product.catalog;

public class InventoryDetails {

    private Long pid;
    private int noOfItemsLeft;

    public InventoryDetails(Long pid, int noOfItemsLeft) {
        this.pid = pid;
        this.noOfItemsLeft = noOfItemsLeft;
    }

    public Long getPid() {
        return pid;
    }

    public int getNoOfItemsLeft() {
        return noOfItemsLeft;
    }
}
