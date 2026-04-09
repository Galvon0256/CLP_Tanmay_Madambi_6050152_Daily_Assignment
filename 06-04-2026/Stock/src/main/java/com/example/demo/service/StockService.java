package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ProductStockNotFoundException;
import com.example.demo.model.Stock;
import com.example.demo.repository.IStockRepo;

@Service
public class StockService implements IStockService{
	@Autowired
	IStockRepo repo;

	@Override
	public Stock getStockById(int id) {
		Optional<Stock> op = repo.findById(id);
		if(op.isPresent()) {
			Stock stock = op.get();
			return stock;
		}
		else {
			throw new ProductStockNotFoundException("Product stock not found");
		}
	}

}
