package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, String> {
    Product findProductById(String id);

    List<Product> findProductByCategory(ProductCategory category);

    List<Product> findProductByProductNameStartsWith(String name);

}
