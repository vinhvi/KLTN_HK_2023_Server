package com.example.demo.controller;

import com.example.demo.entity.ImportOrder;
import com.example.demo.service.ImportOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/importOrders")
public class ImportOrderController {
    private final ImportOrderService importOrderService;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?> saveOrUpdate(@RequestBody ImportOrder importOrder){
        try {
            return ResponseEntity.ok().body(importOrderService.saveOrUpdate(importOrder));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        try {
            List<ImportOrder> importOrders = importOrderService.getAll();
            if (importOrders.isEmpty()){
                return ResponseEntity.badRequest().body("not found!!");
            }
            return ResponseEntity.ok().body(importOrders);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id){
        try {
            ImportOrder importOrder = importOrderService.getById(id);
            if (importOrder == null){
                return ResponseEntity.badRequest().body( id+" not found!!");
            }
            return ResponseEntity.ok().body(importOrder);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }
    @GetMapping("/randomId")
    public ResponseEntity<?> randomId() {
        try {
            return ResponseEntity.ok().body(importOrderService.randomId());
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

}
