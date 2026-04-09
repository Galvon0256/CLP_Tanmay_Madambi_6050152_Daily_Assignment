package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Pricing;

@Repository
public interface IPricingRepo extends JpaRepository<Pricing, Integer>{
}
