package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Order;


import java.util.Date;
import java.util.List;


public interface OrderService {
    Order saveOrUpdate(int idCart,Order order);
    Order update(Order order);
    List<Order> listConfirm();
    List<Order> getOrderByCustomer(Customer customer);
    Order getOrderById(String id);
    List<Order> getAll();
    List<Order> getByDateBetween(int month, int year);
    String randomOrderId();
    Order createNow(Order order);
    List<Order> update(Employee employee, List<Order> orders);

    List<Order> getByDate(Date date);
}
