package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductSpecification;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductSpecificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/productSpecifications")
public class ProductSpecificationController {
    private final ProductSpecificationService productSpecificationService;
    private final ProductService productService;

    @PostMapping("/saveOrUpdate/{productId}")
    public ResponseEntity<?> saveOrUpdate(@RequestBody ProductSpecification productSpecification, @PathVariable("productId") String productId) {
        try {
            Product product = productService.getById(productId);
            if (product == null) {
                return ResponseEntity.badRequest().body(productId + " not found !!");
            }
            productSpecification.setProduct(product);
            ProductSpecification check = productSpecificationService.saveOrUpdate(productSpecification);
            if (check == null) {
                return ResponseEntity.badRequest().body("failed !!");
            }
            return ResponseEntity.ok().body(check);

        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSpecification(@PathVariable("id") int id) {
        try {
            ProductSpecification productSpecification = productSpecificationService.getById(id);
            if (productSpecification == null) {
                return ResponseEntity.ok().body("product specification id: " + id + " not found!!");
            }
            productSpecificationService.delete(productSpecification);
            return ResponseEntity.ok().body("success !!");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        try {
            ProductSpecification productSpecification = productSpecificationService.getById(id);
            if (productSpecification == null) {
                return ResponseEntity.badRequest().body(id + "not found !!");
            }
            return ResponseEntity.ok().body(productSpecification);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @PostMapping("/updateList/{idProduct}")
    public ResponseEntity<?> updateList(@PathVariable("idProduct") String idProduct, @RequestBody List<ProductSpecification> productSpecifications) {
        try {
            Product product = productService.getById(idProduct);
            List<ProductSpecification> productSpecificationList = productSpecificationService.updateList(productSpecifications, product);
            if (productSpecificationList == null) {
                return ResponseEntity.badRequest().body("Error !!");
            }
            return ResponseEntity.ok().body(productSpecificationList);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

}
