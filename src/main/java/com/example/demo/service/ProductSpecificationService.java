package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductSpecification;

import java.util.List;

public interface ProductSpecificationService {
    ProductSpecification saveOrUpdate(ProductSpecification productSpecification);
    ProductSpecification getById(int id);
    void delete(ProductSpecification productSpecification);
    List<ProductSpecification> updateList(List<ProductSpecification> productSpecifications,  Product product);
}
