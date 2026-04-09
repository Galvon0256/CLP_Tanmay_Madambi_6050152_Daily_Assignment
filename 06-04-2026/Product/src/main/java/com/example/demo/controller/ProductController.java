package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	ProductService service;
	
	@GetMapping("/product")
	public List<Product> getAllProducts(){
		return service.getAllProducts();
	} 
	
	@GetMapping("/product/{id}")
	public Product getProductById(@PathVariable int id) {
		return service.getProductById(id);
	}
}
