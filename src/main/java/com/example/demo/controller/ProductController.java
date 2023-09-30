package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
        Product check = productService.saveOrUpdate(product);
        if (check == null) {
            return ResponseEntity.badRequest().body("failed !!");
        }
        return ResponseEntity.ok().body(check);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        List<Product> products = productService.getProducts();
        return ResponseEntity.ok().body(Objects.requireNonNullElse(products, "null !"));
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name) {
        List<Product> products = productService.getByName(name);
        if (products == null) {
            return ResponseEntity.badRequest().body(name + " not found !!");
        }
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        Product product = productService.getById(id);
        if (product == null) {
            return ResponseEntity.badRequest().body(id + " not found !!");
        }
        return ResponseEntity.ok().body(product);
    }
}
