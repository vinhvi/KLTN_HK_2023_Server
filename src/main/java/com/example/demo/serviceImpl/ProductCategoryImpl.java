package com.example.demo.serviceImpl;

import com.example.demo.entity.Category;
import com.example.demo.repository.ProductCategoryRepo;
import com.example.demo.service.ProductCategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductCategoryImpl implements ProductCategoryService {
    private final ProductCategoryRepo productCategoryRepo;

    @Override
    public Category saveOrUpdate(Category category) {
        return productCategoryRepo.save(category);
    }

    @Override
    public List<Category> getProductCategories() {
        return productCategoryRepo.findAll();
    }

    @Override
    public Category getById(int id) {
        return productCategoryRepo.findProductCategoryById(id);
    }

    @Override
    public Category getByName(String name) {
        return productCategoryRepo.findProductCategoryByCategoryName(name);
    }
}
