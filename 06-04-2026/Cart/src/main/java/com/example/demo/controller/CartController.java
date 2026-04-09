package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.AddToCartDTO;
import com.example.demo.model.Cart;
import com.example.demo.model.CartDTO;
import com.example.demo.service.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService service;

    @PostMapping
    public Cart addToCart(@RequestBody AddToCartDTO dto) {
        return service.addToCart(dto.getCartId(), dto.getProductId());
    }

    @GetMapping("/{id}")
    public CartDTO getCart(@PathVariable int id) {
        return service.getCart(id);
    }
}