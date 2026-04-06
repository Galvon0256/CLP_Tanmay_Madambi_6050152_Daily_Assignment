package com.example.cart.cart;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class CartService {

    private final ServiceRegistryClient serviceRegistryClient;
    private final AtomicLong cartIdGenerator = new AtomicLong(1000);
    private final Map<Long, Cart> carts = new ConcurrentHashMap<>();

    public CartService(ServiceRegistryClient serviceRegistryClient) {
        this.serviceRegistryClient = serviceRegistryClient;
    }

    public CartResponse createCartWithProduct(Long pid) {
        ProductCatalog productCatalog = serviceRegistryClient.fetchProductCatalog(pid);
        Long cartId = cartIdGenerator.incrementAndGet();
        Cart cart = new Cart(cartId);
        cart.addProduct(productCatalog);
        carts.put(cartId, cart);
        return new CartResponse(cartId, cart.getProducts());
    }

    public CartDetailsResponse getCart(Long cartId) {
        Cart cart = carts.get(cartId);
        if (cart == null) {
            throw new ResponseStatusException(NOT_FOUND, "Cart not found for id: " + cartId);
        }

        List<Long> pids = cart.getProducts().keySet().stream().toList();
        List<ProductCatalog> recommendations = serviceRegistryClient.fetchRecommendations(pids);
        return new CartDetailsResponse(cart.getCartId(), cart.getProducts(), recommendations);
    }
}


