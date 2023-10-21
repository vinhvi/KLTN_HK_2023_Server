package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.entity.ShoppingCart;
import com.example.demo.entity.CartItem;

import java.util.List;

public interface CartItemService {
    CartItem saveOrUpDate(CartItem cartItem);
    CartItem addToCart(CartItem cartItem);
    Boolean remove(int id);
    CartItem getById(int id);
    List<CartItem> getByCart(ShoppingCart shoppingCart);
    CartItem getByProductAndCart(Product product,ShoppingCart shoppingCart);
}
