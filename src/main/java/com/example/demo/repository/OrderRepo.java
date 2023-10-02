package com.example.demo.repository;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, String> {
    Order getOrderById(String orderId);
    List<Order> getOrderByCustomer(Customer customer);
}
