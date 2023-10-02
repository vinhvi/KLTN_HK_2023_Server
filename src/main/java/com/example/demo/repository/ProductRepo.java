package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, String> {
    Product findProductById(String id);

    List<Product> findProductByCategory(Category category);

    List<Product> findProductByProductNameContaining(String name);

}
