package com.example.demo.service;

import com.example.demo.entity.ShoppingCartDetail;

public interface ShoppingCartDetailService {
    ShoppingCartDetail saveOrUpDate(ShoppingCartDetail shoppingCartDetail);
    Boolean remove(ShoppingCartDetail shoppingCartDetail);
}
