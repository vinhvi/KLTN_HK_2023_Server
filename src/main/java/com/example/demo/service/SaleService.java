package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.entity.Sale;

import java.util.List;

public interface SaleService {
    Sale saveOrUpdate(Sale sale);
    Sale getById(String id);
    String createId();
    List<Sale> getAll();

    List<Sale> getByProduct(Product product);
}
