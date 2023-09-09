package com.example.demo.repository;

import com.example.demo.entity.SpecificationCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificationCategoryRepo extends JpaRepository<SpecificationCategory, Integer> {
    SpecificationCategory findSpecificationCategoriesBySpecCategoryName(String name);
}
