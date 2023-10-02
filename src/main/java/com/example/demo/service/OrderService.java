package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;


import java.util.Date;
import java.util.List;


public interface OrderService {
    Order saveOrUpdate(Order order);

    Order getById(String id);

    List<Order> getOrderByCustomer(Customer customer);

    List<Order> getAll();

    List<Order> getByDate(Date date);

    String randomOrderId();
}
