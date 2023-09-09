package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductSpecification;
import com.example.demo.service.ProductSpecificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/productSpecifications")
public class ProductSpecificationController {
    private final ProductSpecificationService productSpecificationService;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?> saveOrUpdate(@RequestBody ProductSpecification productSpecification) {
        ProductSpecification check = productSpecificationService.saveOrUpdate(productSpecification);
        if (check == null) {
            return ResponseEntity.badRequest().body("failed !!");
        }
        return ResponseEntity.ok().body(check);
    }
}
