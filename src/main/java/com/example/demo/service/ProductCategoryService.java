package com.example.demo.service;

import com.example.demo.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory saveOrUpdate(ProductCategory productCategory);

    List<ProductCategory> getProductCategories();

    ProductCategory getById(int id);

    ProductCategory getByName(String name);
}
