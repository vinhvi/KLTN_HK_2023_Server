package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;


import java.util.Date;
import java.util.List;


public interface OrderService {
    Order saveOrUpdate(int idCart,Order order);
    List<Order> listConfirm();
    List<Order> getOrderByCustomer(Customer customer);
    Order getOrderById(String id);
    List<Order> getAll();
    List<Order> getByDate(Date date);
    String randomOrderId();
    Order createNow(Order order);
    List<Order> update(List<Order> orders);
}
