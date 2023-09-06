package com.example.demo.service;

import com.example.demo.entity.Supplier;

import java.util.List;

public interface SupplierService {
    Supplier saveOrUpdate(Supplier supplier);

    Supplier getById(int id);

    Supplier getByName(String name);

    List<Supplier> getSuppliers();
}
