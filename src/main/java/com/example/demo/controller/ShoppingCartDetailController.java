package com.example.demo.controller;

import com.example.demo.entity.Image;
import com.example.demo.entity.ShoppingCartDetail;
import com.example.demo.service.ShoppingCartDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/shoppingCartDetails")
public class ShoppingCartDetailController {
    private final ShoppingCartDetailService cartDetailService;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?> saveOrUpdate(@RequestBody ShoppingCartDetail shoppingCartDetail) {
        ShoppingCartDetail check = cartDetailService.saveOrUpDate(shoppingCartDetail);
        if (check == null) {
            return ResponseEntity.badRequest().body("Failed !!");
        }
        return ResponseEntity.ok().body(check);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (Boolean.FALSE.equals(cartDetailService.remove(id))) {
            return ResponseEntity.badRequest().body("Failed!!");
        }
        return ResponseEntity.ok().body("Success!!");
    }
}
