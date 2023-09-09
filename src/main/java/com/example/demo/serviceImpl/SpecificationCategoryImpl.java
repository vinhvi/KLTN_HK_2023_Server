package com.example.demo.serviceImpl;

import com.example.demo.entity.SpecificationCategory;
import com.example.demo.repository.SpecificationCategoryRepo;
import com.example.demo.service.SpecificationCategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SpecificationCategoryImpl implements SpecificationCategoryService {
    private final SpecificationCategoryRepo specificationCategoryRepo;

    @Override
    public SpecificationCategory saveOrUpdate(SpecificationCategory specificationCategory) {
        return specificationCategoryRepo.save(specificationCategory);
    }

    @Override
    public List<SpecificationCategory> getAll() {
        return specificationCategoryRepo.findAll();
    }

    @Override
    public SpecificationCategory getByName(String name) {
        return specificationCategoryRepo.findSpecificationCategoriesBySpecCategoryName(name);
    }
}
