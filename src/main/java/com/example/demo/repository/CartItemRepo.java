package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.ShoppingCart;
import com.example.demo.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Integer> {
    CartItem findShoppingCartDetailById(int id);

    CartItem findShoppingCartDetailByProductAndAndShoppingCart(Product product, ShoppingCart shoppingCart);

    List<CartItem> findShoppingCartDetailByShoppingCart(ShoppingCart shoppingCart);
}
