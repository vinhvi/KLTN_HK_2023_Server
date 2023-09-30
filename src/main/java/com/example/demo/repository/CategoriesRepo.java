package com.example.demo.repository;

import com.example.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepo extends JpaRepository<Category, Integer> {
    Category findProductCategoryById(int id);
    Category findProductCategoryByCategoryName(String name);


}
