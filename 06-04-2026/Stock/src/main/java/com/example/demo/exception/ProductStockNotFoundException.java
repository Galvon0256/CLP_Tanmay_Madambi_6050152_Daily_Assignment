package com.example.demo.exception;

public class ProductStockNotFoundException extends RuntimeException{

	public ProductStockNotFoundException(String message) {
		super(message);
	}
	
}
