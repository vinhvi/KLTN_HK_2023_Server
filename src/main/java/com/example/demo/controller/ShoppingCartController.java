package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.entity.ShoppingCart;
import com.example.demo.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/shoppingCarts")
public class ShoppingCartController {
    private final ShoppingCartService service;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?> saveOrUpdate(@RequestBody ShoppingCart shoppingCart) {
        try {
            ShoppingCart check = service.saveOrUpdate(shoppingCart);
            if (check == null) {
                return ResponseEntity.badRequest().body("Failed !!");
            }
            return ResponseEntity.ok().body(check);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }

    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getByName(@PathVariable("id") int id) {
        try {
            ShoppingCart shoppingCart = service.getById(id);
            if (shoppingCart == null) {
                return ResponseEntity.badRequest().body(id + " not found !!");
            }
            return ResponseEntity.ok().body(shoppingCart);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getByCustomer")
    public ResponseEntity<?> getByName(@RequestBody Customer customer) {
        try {
            ShoppingCart shoppingCart = service.getByCustomer(customer);
            if (shoppingCart == null) {
                return ResponseEntity.badRequest().body(customer.getId() + " not found !!");
            }
            return ResponseEntity.ok().body(shoppingCart);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

}
