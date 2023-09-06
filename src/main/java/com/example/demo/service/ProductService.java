package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductCategory;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    List<Product> getByCategory(ProductCategory productCategory);
    List<Product> getByName(String name);
    Product getById(String id);
    Product saveOrUpdate(Product product);
    String randomId();
}
