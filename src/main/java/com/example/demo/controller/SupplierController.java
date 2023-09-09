package com.example.demo.controller;

import com.example.demo.entity.Supplier;
import com.example.demo.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/suppliers")
public class SupplierController {
    private final SupplierService supplierService;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?> saveOrUpdate(@RequestBody Supplier supplier) {
        Supplier check = supplierService.saveOrUpdate(supplier);
        if (check == null) {
            return ResponseEntity.badRequest().body("failed !!");
        }
        return ResponseEntity.ok().body(check);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        List<Supplier> suppliers = supplierService.getSuppliers();
        return ResponseEntity.ok().body(Objects.requireNonNullElse(suppliers, "null !"));
    }

    @GetMapping("/getBySupplierName/{name}")
    public ResponseEntity<?> getByCategoryName(@PathVariable("name") String name) {
        Supplier supplier = supplierService.getByName(name);
        if (supplier == null) {
            return ResponseEntity.badRequest().body(name + " not found !!");
        }
        return ResponseEntity.ok().body(supplier);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getByCategoryId(@PathVariable("id") int id) {
        Supplier supplier = supplierService.getById(id);
        if (supplier == null) {
            return ResponseEntity.badRequest().body(id + " not found !!");
        }
        return ResponseEntity.ok().body(supplier);
    }
}
