package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Product;

public interface IProductService {
	public Product getProductById(int id);
	public List<Product> getAllProducts();

}
