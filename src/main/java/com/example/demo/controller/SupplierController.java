package com.example.demo.controller;

import com.example.demo.entity.Supplier;
import com.example.demo.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/suppliers")
public class SupplierController {
    private final SupplierService supplierService;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?> saveOrUpdate(@RequestBody Supplier supplier) {
        try {
            Supplier check1 = supplierService.getByEmail(supplier.getEmail());
            if (check1 != null) {
                return ResponseEntity.badRequest().body(supplier.getEmail() + " is ready in database!!");
            }
            Supplier check2 = supplierService.getByPhone(supplier.getPhone());
            if (check2 != null) {
                return ResponseEntity.badRequest().body(supplier.getPhone() + " is ready in database!!");
            }
            return ResponseEntity.ok().body(supplierService.saveOrUpdate(supplier));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }

    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            List<Supplier> suppliers = supplierService.getSuppliers();
            if (suppliers.isEmpty()) {
                return ResponseEntity.ok().body("There are no suppliers in the database yet");
            }
            return ResponseEntity.ok().body(suppliers);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }

    }

    @GetMapping("/getBySupplierName/{name}")
    public ResponseEntity<?> getByCategoryName(@PathVariable("name") String name) {
        try {
            List<Supplier> suppliers = supplierService.getByName(name);
            if (suppliers.isEmpty()) {
                return ResponseEntity.badRequest().body(name + " not found !!");
            }
            return ResponseEntity.ok().body(suppliers);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getByCategoryId(@PathVariable("id") int id) {
        try {
            Supplier supplier = supplierService.getById(id);
            if (supplier == null) {
                return ResponseEntity.badRequest().body(id + " not found !!");
            }
            return ResponseEntity.ok().body(supplier);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getByEmailOrPhone/{key}")
    public ResponseEntity<?> getByEmailOrPhone(@PathVariable("key") String key) {
        try {
            Supplier supplierEmailOrPhone = supplierService.getByEmailOrPhone(key, key);
            if (supplierEmailOrPhone != null) {
                return ResponseEntity.ok().body(supplierEmailOrPhone);
            }
            return ResponseEntity.ok().body(key + " not found!!");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }
}
