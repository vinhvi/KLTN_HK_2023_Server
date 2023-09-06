package com.example.demo.repository;

import com.example.demo.entity.Customer;
import com.example.demo.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepo extends JpaRepository<ShoppingCart,Integer> {
    ShoppingCart findShoppingCartById(int id);
    ShoppingCart findShoppingCartByCustomer(Customer customer);
}
