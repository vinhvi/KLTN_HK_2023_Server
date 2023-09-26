package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/category")
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?> saveOrUpdate(@RequestBody Category category) {
        Category check = productCategoryService.saveOrUpdate(category);
        if (check == null) {
            return ResponseEntity.badRequest().body("failed !!");
        }
        return ResponseEntity.ok().body(check);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        List<Category> categories = productCategoryService.getProductCategories();
        return ResponseEntity.ok().body(Objects.requireNonNullElse(categories, "null !"));
    }

    @GetMapping("/getByCategoryName/{categoryName}")
    public ResponseEntity<?> getByCategoryName(@PathVariable("categoryName") String categoryName) {
        Category category = productCategoryService.getByName(categoryName);
        if (category == null) {
            return ResponseEntity.badRequest().body(categoryName + " not found !!");
        }
        return ResponseEntity.ok().body(category);
    }

    @GetMapping("/getByCategoryId/{id}")
    public ResponseEntity<?> getByCategoryId(@PathVariable("id") int id) {
        Category category = productCategoryService.getById(id);
        if (category == null) {
            return ResponseEntity.badRequest().body(id + " not found !!");
        }
        return ResponseEntity.ok().body(category);
    }
}
