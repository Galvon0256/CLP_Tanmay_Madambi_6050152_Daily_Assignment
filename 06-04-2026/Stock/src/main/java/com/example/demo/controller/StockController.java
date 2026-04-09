package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Stock;
import com.example.demo.service.StockService;

@RestController
public class StockController {
	@Autowired
	StockService service;
	
	@GetMapping("/stock/{id}")
	public Stock getStockById(@PathVariable int id) {
		return service.getStockById(id);
	}

}
