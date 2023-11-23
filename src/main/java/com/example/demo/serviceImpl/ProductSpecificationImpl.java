package com.example.demo.serviceImpl;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductSpecification;
import com.example.demo.repository.ProductSpecificationRepo;
import com.example.demo.service.ProductSpecificationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public ProductSpecification getById(int id) {
        return productSpecificationRepo.findProductSpecificationById(id);
    }

    @Override
    public void delete(ProductSpecification productSpecification) {
        productSpecificationRepo.delete(productSpecification);
    }

    @Override
    public List<ProductSpecification> updateList(List<ProductSpecification> productSpecifications, Product product) {
        List<ProductSpecification> productSpecificationList = product.getSpecifications();
        List<ProductSpecification> productSpecificationSave = new ArrayList<>();
        productSpecificationRepo.deleteAll(productSpecificationList);
        for (ProductSpecification productSpecification : productSpecifications) {
            productSpecification.setProduct(product);
            productSpecificationSave.add(productSpecificationRepo.save(productSpecification));
        }
        return productSpecificationSave;
    }
}
