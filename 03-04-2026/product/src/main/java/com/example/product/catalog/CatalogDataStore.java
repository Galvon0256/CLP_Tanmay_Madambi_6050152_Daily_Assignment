package com.example.product.catalog;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CatalogDataStore {

    private final Map<Long, ProductDetails> products = List.of(
            new ProductDetails(1L, "iPhone 15", "electronics"),
            new ProductDetails(2L, "Galaxy S24", "electronics"),
            new ProductDetails(3L, "Running Shoes", "sports"),
            new ProductDetails(4L, "Football", "sports"),
            new ProductDetails(5L, "Coffee Mug", "home")
    ).stream().collect(Collectors.toUnmodifiableMap(ProductDetails::getPid, Function.identity()));

    private final Map<Long, PricingDetails> pricing = List.of(
            new PricingDetails(1L, 90000, 5000),
            new PricingDetails(2L, 78000, 4000),
            new PricingDetails(3L, 5000, 500),
            new PricingDetails(4L, 1200, 100),
            new PricingDetails(5L, 450, 50)
    ).stream().collect(Collectors.toUnmodifiableMap(PricingDetails::getPid, Function.identity()));

    private final Map<Long, InventoryDetails> inventory = List.of(
            new InventoryDetails(1L, 15),
            new InventoryDetails(2L, 9),
            new InventoryDetails(3L, 40),
            new InventoryDetails(4L, 55),
            new InventoryDetails(5L, 120)
    ).stream().collect(Collectors.toUnmodifiableMap(InventoryDetails::getPid, Function.identity()));

    public List<ProductDetails> findAllProducts() {
        return products.values().stream().toList();
    }

    public ProductDetails findProductById(Long pid) {
        return products.get(pid);
    }

    public PricingDetails findPricingById(Long pid) {
        return pricing.get(pid);
    }

    public InventoryDetails findInventoryById(Long pid) {
        return inventory.get(pid);
    }
}


