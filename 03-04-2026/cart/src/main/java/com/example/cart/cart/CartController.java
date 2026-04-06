package com.example.cart.cart;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @Operation(summary = "Create a cart with one product")
    @PostMapping
    public CartResponse addProduct(@Valid @RequestBody AddToCartRequest request) {
        return cartService.createCartWithProduct(request.getPid());
    }

    @GetMapping("/{id}")
    public CartDetailsResponse getCart(@PathVariable("id") Long id) {
        return cartService.getCart(id);
    }
}


