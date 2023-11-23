package com.example.demo.repository;

import com.example.demo.entity.ProductSpecification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSpecificationRepo extends JpaRepository<ProductSpecification,Integer> {
    ProductSpecification findProductSpecificationById(int id);

}
