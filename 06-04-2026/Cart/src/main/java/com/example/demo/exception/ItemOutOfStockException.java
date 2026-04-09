package com.example.demo.exception;

public class ItemOutOfStockException extends RuntimeException{

	public ItemOutOfStockException(String message) {
		super(message);
	}
	
}
