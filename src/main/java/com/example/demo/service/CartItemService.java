package com.example.demo.service;

import com.example.demo.entity.ShoppingCart;
import com.example.demo.entity.CartItem;

import java.util.List;

public interface CartItemService {
    CartItem saveOrUpDate(CartItem cartItem);
    Boolean remove(int id);
    CartItem getById(int id);
    List<CartItem> getByCart(ShoppingCart shoppingCart);
}
