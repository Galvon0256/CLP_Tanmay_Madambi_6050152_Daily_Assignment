package com.example.cart.cart;

import java.util.List;
import java.util.Map;

public class CartDetailsResponse {

    private Long cartId;
    private Map<Long, ProductCatalog> products;
    private List<ProductCatalog> recommendations;

    public CartDetailsResponse() {
    }

    public CartDetailsResponse(Long cartId, Map<Long, ProductCatalog> products, List<ProductCatalog> recommendations) {
        this.cartId = cartId;
        this.products = products;
        this.recommendations = recommendations;
    }

    public Long getCartId() {
        return cartId;
    }

    public Map<Long, ProductCatalog> getProducts() {
        return products;
    }

    public List<ProductCatalog> getRecommendations() {
        return recommendations;
    }
}
