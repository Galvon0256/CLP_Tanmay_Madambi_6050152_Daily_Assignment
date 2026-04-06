package com.example.cart.cart;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {

    private final Long cartId;
    private final Map<Long, ProductCatalog> products = new LinkedHashMap<>();

    public Cart(Long cartId) {
        this.cartId = cartId;
    }

    public Long getCartId() {
        return cartId;
    }

    public Map<Long, ProductCatalog> getProducts() {
        return products;
    }

    public void addProduct(ProductCatalog productCatalog) {
        products.put(productCatalog.getPid(), productCatalog);
    }
}


