package com.example.cart.cart;

import java.util.Map;

public class CartResponse {

    private Long cartId;
    private Map<Long, ProductCatalog> products;

    public CartResponse() {
    }

    public CartResponse(Long cartId, Map<Long, ProductCatalog> products) {
        this.cartId = cartId;
        this.products = products;
    }

    public Long getCartId() {
        return cartId;
    }

    public Map<Long, ProductCatalog> getProducts() {
        return products;
    }
}
