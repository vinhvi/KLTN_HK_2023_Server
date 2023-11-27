package com.example.demo.controller;

import com.example.demo.entity.ImportOrder;
import com.example.demo.entity.LoHang;
import com.example.demo.entity.Product;
import com.example.demo.service.ImportOrderService;
import com.example.demo.service.LoHangService;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/loHangs")
public class LoHangController {
    private final LoHangService loHangService;
    private final ImportOrderService importOrderService;
    private final ProductService productService;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?> saveOrUpdate(@RequestBody LoHang loHang) {
        try {
            return ResponseEntity.ok().body(loHangService.saveOrUpdate(loHang));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getById/{idLH}")
    public ResponseEntity<?> getById(@PathVariable("idLH") String idLH) {
        try {
            LoHang loHang = loHangService.getById(idLH);
            if (loHang == null) {
                return ResponseEntity.badRequest().body(idLH + " not found!");
            }
            return ResponseEntity.ok().body(loHang);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getByImportOrder/{id}")
    public ResponseEntity<?> getLoHangByImportOrder(@PathVariable("id") String id) {
        try {
            ImportOrder importOrder = importOrderService.getById(id);
            List<LoHang> loHangs = loHangService.getByImportOrder(importOrder);
            if (loHangs.isEmpty()) {
                return ResponseEntity.badRequest().body(id + " not found!");
            }
            return ResponseEntity.ok().body(loHangs);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getByProduct/{id}")
    public ResponseEntity<?> getLoHangByProduct(@PathVariable("id") String id) {
        try {
            Product product = productService.getById(id);
            List<LoHang> loHangs = loHangService.getByProduct(product);
            if (loHangs.isEmpty()) {
                return ResponseEntity.badRequest().body(id + " not found!");
            }
            return ResponseEntity.ok().body(loHangs);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }
}
