package com.example.demo.repository;

import com.example.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,String> {
    Customer findCustomerById(String id);
    Customer findByPhone(String phone);
    Customer findCustomerByEmail(String email);
}
