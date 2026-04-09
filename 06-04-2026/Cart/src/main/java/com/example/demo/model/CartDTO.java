package com.example.demo.model;

import java.util.*;

import com.example.demo.model.Catalog;

public class CartDTO {

    private int cartId;
    private Map<Integer, Catalog> products;
    private List<Catalog> recommendations;

    public int getCartId() {
        return cartId;
    }
    public void setCartId(int cartId) {
        this.cartId = cartId;
    }
    public Map<Integer, Catalog> getProducts() {
        return products;
    }
    public void setProducts(Map<Integer, Catalog> products) {
        this.products = products;
    }
    public List<Catalog> getRecommendations() {
        return recommendations;
    }
    public void setRecommendations(List<Catalog> recommendations) {
        this.recommendations = recommendations;
    }
}