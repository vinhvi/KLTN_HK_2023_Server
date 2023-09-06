package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart saveOrUpdate(ShoppingCart shoppingCart);
    ShoppingCart getById(int id);
    ShoppingCart getByCustomer(Customer customer);
}
