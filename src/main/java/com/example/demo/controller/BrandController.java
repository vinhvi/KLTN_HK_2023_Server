package com.example.demo.controller;

import com.example.demo.entity.Brand;
import com.example.demo.entity.Category;
import com.example.demo.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/brands")
public class BrandController {
    private final BrandService brandService;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?> saveOrUpdate(@RequestBody Brand brand) {
        try {
            if (brandService.getByName(brand.getName()) != null) {
                return ResponseEntity.ok().body(brand.getName() + " is ready in database!!");
            }
            return ResponseEntity.ok().body(brandService.saveOrUpdate(brand));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("save is failed!! " + exception);
        }
    }

    @GetMapping("/getAllBrand")
    public ResponseEntity<?> getAllBrand() {
        try {
            List<Brand> brandList = brandService.getAll();
            if (brandList.isEmpty()) {
                return ResponseEntity.ok().body("There are no brands in the database yet!!");
            }
            return ResponseEntity.ok().body(brandList);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("Had a exception !!" + exception);
        }
    }
}
