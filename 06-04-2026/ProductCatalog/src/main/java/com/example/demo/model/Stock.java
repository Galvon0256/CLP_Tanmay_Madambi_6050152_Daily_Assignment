package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

public class Stock {
	private int id;
	private int itemsLeft;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id; 
	}
	public int getItemsLeft() {
		return itemsLeft;
	}
	public void setItemsLeft(int itemsLeft) {
		this.itemsLeft = itemsLeft;
	}
}
