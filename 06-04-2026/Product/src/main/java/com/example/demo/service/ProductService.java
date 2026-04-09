package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.IProductRepo;

@Service
public class ProductService implements IProductService{
	@Autowired
	IProductRepo repo;

	@Override
	public Product getProductById(int id) {
		Optional<Product> op= repo.findById(id);
		if(op.isPresent()) {
			Product product = op.get();
			return product;
		}
		else {
			throw new ProductNotFoundException("Product not found.");
		}
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> products = repo.findAll();
		return products;
	}

}
