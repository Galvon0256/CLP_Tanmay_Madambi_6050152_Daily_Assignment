package com.example.demo.model;

public class Catalog {
	private int id;
    private String name;
    private String category;
    private double offerPrice;
    private int itemsLeft;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getOfferPrice() {
		return offerPrice;
	}
	public void setOfferPrice(double offerPrice) {
		this.offerPrice = offerPrice;
	}
	public int getItemsLeft() {
		return itemsLeft;
	}
	public void setItemsLeft(int itemsLeft) {
		this.itemsLeft = itemsLeft;
	}
}
