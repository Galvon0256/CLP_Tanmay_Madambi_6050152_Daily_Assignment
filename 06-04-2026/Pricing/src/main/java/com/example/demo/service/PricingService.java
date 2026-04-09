package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ProductPricingNotFoundException;
import com.example.demo.model.Pricing;
import com.example.demo.repository.IPricingRepo;

@Service
public class PricingService implements IPricingService {
	@Autowired
	IPricingRepo repo;

	@Override
	public Pricing getPriceById(int id){
		Optional<Pricing> op= repo.findById(id);
		if(op.isPresent()) {
			Pricing pricing = op.get();
			return pricing;
		}
		else {
			throw new ProductPricingNotFoundException("Product pricing not found");
		}
	}

}
