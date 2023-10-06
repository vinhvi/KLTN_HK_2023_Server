package com.example.demo.service;

import com.example.demo.DataBean.ShoppingCartDataBean;
import com.example.demo.entity.Customer;
import com.example.demo.entity.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart saveOrUpdate(ShoppingCart shoppingCart);
    ShoppingCartDataBean getById(int id);
    ShoppingCartDataBean getByCustomer(Customer customer);
    ShoppingCart getByIdCart(int id);

}
