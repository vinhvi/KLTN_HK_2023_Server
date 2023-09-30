package com.example.demo.service;

import com.example.demo.entity.Category;

import java.util.List;

public interface CategoriesService {
    Category saveOrUpdate(Category category);

    List<Category> getProductCategories();

    Category getById(int id);

    Category getByName(String name);
}
