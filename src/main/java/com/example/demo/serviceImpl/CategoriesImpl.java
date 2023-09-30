package com.example.demo.serviceImpl;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoriesRepo;
import com.example.demo.service.CategoriesService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CategoriesImpl implements CategoriesService {
    private final CategoriesRepo categoriesRepo;

    @Override
    public Category saveOrUpdate(Category category) {
        return categoriesRepo.save(category);
    }

    @Override
    public List<Category> getProductCategories() {
        return categoriesRepo.findAll();
    }

    @Override
    public Category getById(int id) {
        return categoriesRepo.findProductCategoryById(id);
    }

    @Override
    public Category getByName(String name) {
        return categoriesRepo.findProductCategoryByCategoryName(name);
    }
}
