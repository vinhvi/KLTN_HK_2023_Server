package com.example.demo.repository;

import com.example.demo.entity.ShoppingCartDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartDetailRepo extends JpaRepository<ShoppingCartDetail,Integer> {
    ShoppingCartDetail findShoppingCartDetailById(int id);
}