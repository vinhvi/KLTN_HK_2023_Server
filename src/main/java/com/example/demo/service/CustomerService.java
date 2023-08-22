package com.example.demo.service;

import com.example.demo.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer addCustomer(Customer customer) ;

    Customer getById(String id);

    List<Customer> getAll();
    Customer getByEmail(String email);

    Customer getByPhone(String phone);

    String randomId();
}
