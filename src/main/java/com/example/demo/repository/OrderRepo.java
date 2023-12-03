package com.example.demo.repository;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, String> {
    Order findOrderById(String orderId);

    List<Order> findOrderByCustomer(Customer customer);
    List<Order> findOrderByDateBetween(Date start, Date end);
    @Query("SELECT o FROM Order o WHERE DATE(o.date) = :date")
    List<Order> findOrderByDate(@Param("date") Date date);
}
