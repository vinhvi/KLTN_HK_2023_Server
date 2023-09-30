package com.example.demo.repository;

import com.example.demo.entity.ShoppingCart;
import com.example.demo.entity.ShoppingCartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface ShoppingCartDetailRepo extends JpaRepository<ShoppingCartDetail, Integer> {
    ShoppingCartDetail findShoppingCartDetailById(int id);

    List<ShoppingCartDetail> findShoppingCartDetailByShoppingCart(ShoppingCart shoppingCart);
}
