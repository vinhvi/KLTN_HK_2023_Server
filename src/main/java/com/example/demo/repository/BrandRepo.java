package com.example.demo.repository;

import com.example.demo.entity.Brand;
import com.example.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepo extends JpaRepository<Brand, Integer> {
    Brand findBrandById(int id);

    Brand findBrandByName(String name);
}
