package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductSpecification;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductSpecificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/productSpecifications")
public class ProductSpecificationController {
    private final ProductSpecificationService productSpecificationService;
    private final ProductService productService;
    @PostMapping("/saveOrUpdate/{productId}")
    public ResponseEntity<?> saveOrUpdate(@RequestBody ProductSpecification productSpecification, @PathVariable("productId") String productId) {
        Product product =  productService.getById(productId);
        if (product == null){
            return ResponseEntity.badRequest().body("productId not found !!");
        }
        productSpecification.setProduct(product);
        ProductSpecification check = productSpecificationService.saveOrUpdate(productSpecification);
        if (check == null) {
            return ResponseEntity.badRequest().body("failed !!");
        }
        return ResponseEntity.ok().body(check);
    }
}
