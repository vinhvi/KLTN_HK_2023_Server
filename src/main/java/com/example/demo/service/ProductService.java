package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.entity.Category;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    List<Product> getByCategory(Category category);
    List<Product> getAll();
    List<Product> getByName(String name);
    Product getById(String id);
    Product saveOrUpdate(Product product);
    String randomId();
    List<Product> listNeedUpdate();
    void delete(Product product);
}
