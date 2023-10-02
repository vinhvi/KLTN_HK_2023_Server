package com.example.demo.repository;

import com.example.demo.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepo extends JpaRepository<Supplier, Integer> {
    Supplier findSupplierById(int id);

    List<Supplier> findSupplierByNameContaining(String name);

    Supplier findSupplierByEmailOrPhone(String email, String phone);

    Supplier findSupplierByEmail(String email);

    Supplier findSupplierByPhone(String phone);
}
