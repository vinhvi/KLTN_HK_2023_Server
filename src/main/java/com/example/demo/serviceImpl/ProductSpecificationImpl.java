package com.example.demo.serviceImpl;

import com.example.demo.entity.ProductCategory;
import com.example.demo.entity.ProductSpecification;
import com.example.demo.repository.ProductSpecificationRepo;
import com.example.demo.service.ProductCategoryService;
import com.example.demo.service.ProductSpecificationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductSpecificationImpl implements ProductSpecificationService {
    private final ProductSpecificationRepo productSpecificationRepo;

    @Override
    public ProductSpecification saveOrUpdate(ProductSpecification productSpecification) {
        return productSpecificationRepo.save(productSpecification);
    }
}
