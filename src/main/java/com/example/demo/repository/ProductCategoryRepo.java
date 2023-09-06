package com.example.demo.repository;

import com.example.demo.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Integer> {
    ProductCategory findProductCategoryById(int id);
    ProductCategory findProductCategoryByCategoryName(String name);
}
