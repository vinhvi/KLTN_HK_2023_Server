package com.example.demo.service;

import com.example.demo.entity.ShoppingCartDetail;

import java.util.List;

public interface ShoppingCartDetailService {
    ShoppingCartDetail saveOrUpDate(ShoppingCartDetail shoppingCartDetail);
    Boolean remove(int id);
    ShoppingCartDetail getById(int id);
    List<ShoppingCartDetail> getByCart(int id);
}
