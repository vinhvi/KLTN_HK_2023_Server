package com.example.demo.controller;

import com.example.demo.entity.Brand;
import com.example.demo.entity.Category;
import com.example.demo.service.BrandService;
import com.example.demo.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/category")
public class CategoriesController {
    private final CategoriesService categoriesService;
    private final BrandService brandService;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?> saveOrUpdate(@RequestBody Category category) {
        try {
            if (categoriesService.getByName(category.getCategoryName()) != null) {
                return ResponseEntity.ok().body(category.getCategoryName() + " is ready in database!!");
            }
            return ResponseEntity.ok().body(categoriesService.saveOrUpdate(category));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("save is failed !!" + exception);
        }

    }

    @PostMapping("/addBrandForCategories/{id}")
    public ResponseEntity<?> addBrandForCategories(@RequestBody List<Brand> brandList, @PathVariable("id") int id) {
        try {
            Category category = categoriesService.getById(id);
            if (category == null) {
                return ResponseEntity.ok().body(id + "not found!!");
            }
            Set<Brand> newBrands = new LinkedHashSet<>();
            if (!category.getBrands().isEmpty()) {
                newBrands.addAll(category.getBrands());
            }
            for (Brand brand : brandList) {
                Brand brandCheck = brandService.getById(brand.getId());
                if (brandCheck == null) {
                    return ResponseEntity.ok().body("brand id: " + brand.getId() + "not found!!");
                }
                newBrands.add(brand);
            }
            category.getBrands().addAll(newBrands);
            categoriesService.saveOrUpdate(category);
            return ResponseEntity.ok().body(category);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("Had a exception !!" + exception);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            List<Category> categories = categoriesService.getProductCategories();
            if (categories.isEmpty()) {
                return ResponseEntity.ok().body("There are no categories in the database yet!!");
            }
            return ResponseEntity.ok().body(categories);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("Had a exception !!" + exception);
        }

    }

    @GetMapping("/getByCategoryName/{categoryName}")
    public ResponseEntity<?> getByCategoryName(@PathVariable("categoryName") String categoryName) {
        try {
            Category category = categoriesService.getByName(categoryName);
            if (category == null) {
                return ResponseEntity.badRequest().body(categoryName + " not found !!");
            }
            return ResponseEntity.ok().body(category);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("Had a exception !!" + exception);
        }
    }

    @GetMapping("/getByCategoryId/{id}")
    public ResponseEntity<?> getByCategoryId(@PathVariable("id") int id) {
        try {
            Category category = categoriesService.getById(id);
            if (category == null) {
                return ResponseEntity.badRequest().body(id + " not found !!");
            }
            return ResponseEntity.ok().body(category);

        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("Had a exception !!" + exception);
        }

    }
}
