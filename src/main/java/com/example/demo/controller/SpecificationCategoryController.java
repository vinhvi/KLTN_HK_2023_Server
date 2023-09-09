package com.example.demo.controller;

import com.example.demo.entity.SpecificationCategory;
import com.example.demo.service.SpecificationCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/specificationCategories")
public class SpecificationCategoryController {
    private final SpecificationCategoryService specificationCategoryService;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?> saveOrUpdate(@RequestBody SpecificationCategory specificationCategory) {
        SpecificationCategory check = specificationCategoryService.saveOrUpdate(specificationCategory);
        if (check == null) {
            return ResponseEntity.badRequest().body("Failed !!");
        }
        return ResponseEntity.ok().body(check);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name) {
        SpecificationCategory specificationCategory = specificationCategoryService.getByName(name);
        if (specificationCategory == null) {
            return ResponseEntity.badRequest().body(name + " not found !!");
        }
        return ResponseEntity.ok().body(specificationCategory);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getByAll() {
        List<SpecificationCategory> specificationCategories = specificationCategoryService.getAll();
        if (specificationCategories == null) {
            return ResponseEntity.badRequest().body(" not found !!");
        }
        return ResponseEntity.ok().body(specificationCategories);
    }


}
