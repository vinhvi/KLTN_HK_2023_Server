package com.example.demo.repository;

import com.example.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, String> {
    Customer findCustomerById(String id);

    Customer findByPhone(String phone);

    Customer findCustomerByEmail(String email);

    Customer findCustomerByPhoneOrEmail(String phone, String email);
}
