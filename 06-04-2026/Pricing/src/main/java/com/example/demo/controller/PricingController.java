package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Pricing;
import com.example.demo.service.PricingService;

@RestController
public class PricingController {
	@Autowired
	PricingService service;
	
	@GetMapping("/pricing/{id}")
	public Pricing getPricingById(@PathVariable int id) {
		return service.getPriceById(id);
	}
}
