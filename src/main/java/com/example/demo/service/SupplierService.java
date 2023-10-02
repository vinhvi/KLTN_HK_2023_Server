package com.example.demo.service;

import com.example.demo.entity.Supplier;

import java.util.List;

public interface SupplierService {
    Supplier saveOrUpdate(Supplier supplier);

    Supplier getById(int id);

    List<Supplier> getByName(String name);

    List<Supplier> getSuppliers();
    Supplier getByEmailOrPhone(String email,String phone);
    Supplier getByPhone(String phone);
    Supplier getByEmail(String email);
}
