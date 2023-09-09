package com.example.demo.service;

import com.example.demo.entity.SpecificationCategory;

import java.util.List;

public interface SpecificationCategoryService {
    SpecificationCategory saveOrUpdate(SpecificationCategory specificationCategory);
    List<SpecificationCategory> getAll();
    SpecificationCategory getByName(String name);
}
