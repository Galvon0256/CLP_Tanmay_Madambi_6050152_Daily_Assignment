package com.example.demo.service;

import com.example.demo.model.Cart;
import com.example.demo.model.CartDTO;

public interface ICartService {
	public Cart addToCart(int cartId, int productId);
	public CartDTO getCart(int cartId);
}
