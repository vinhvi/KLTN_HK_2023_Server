package com.example.demo.repository;

import com.example.demo.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepo extends JpaRepository<Supplier,Integer> {
    Supplier findSupplierById(int id);
    Supplier findSupplierByName(String name);
}
