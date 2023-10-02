package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/randomId")
    public ResponseEntity<?> randomId() {
        try {
            return ResponseEntity.ok().body(productService.randomId());
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute!!" + exception);
        }
    }

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?> saveOrUpdate(@RequestBody Product product) {
        try {
            Product check = productService.saveOrUpdate(product);
            if (check == null) {
                return ResponseEntity.badRequest().body("failed !!");
            }
            return ResponseEntity.ok().body(check);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            List<Product> products = productService.getProducts();
            if (products.isEmpty()) {
                return ResponseEntity.ok().body("There are no products in the database yet!!");
            }
            return ResponseEntity.ok().body(products);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name) {
        try {
            List<Product> products = productService.getByName(name);
            if (products.isEmpty()) {
                return ResponseEntity.badRequest().body(name + " not found !!");
            }
            return ResponseEntity.ok().body(products);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        try {
            Product product = productService.getById(id);
            if (product == null) {
                return ResponseEntity.badRequest().body(id + " not found !!");
            }
            return ResponseEntity.ok().body(product);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }

    }
}
