package com.example.demo.controller;

import com.example.demo.entity.ImportOrderDetail;
import com.example.demo.service.ImportOrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/importOrderDetail")
public class ImportOrderDetailController {
    private final ImportOrderDetailService service;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?> saveOrUpdate(@RequestBody ImportOrderDetail importOrderDetail) {
        try {
            return ResponseEntity.ok().body(service.saveOrUpdate(importOrderDetail));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        try {
            ImportOrderDetail importOrderDetail = service.getById(id);
            if (importOrderDetail == null) {
                return ResponseEntity.badRequest().body(id + " not found!!");
            }
            return ResponseEntity.ok().body(importOrderDetail);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        try {
            ImportOrderDetail importOrderDetail = service.getById(id);
            if (importOrderDetail == null) {
                return ResponseEntity.badRequest().body(id + " not found!!");
            }
            service.deleteById(importOrderDetail);
            return ResponseEntity.ok().body("success!!");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

}
