package com.example.demo.controller;

import com.example.demo.DataBean.ShoppingCartDataBean;
import com.example.demo.entity.ShoppingCart;
import com.example.demo.entity.ShoppingCartDetail;
import com.example.demo.service.ShoppingCartDetailService;
import com.example.demo.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/shoppingCartDetails")
public class ShoppingCartDetailController {
    private final ShoppingCartDetailService cartDetailService;
    private final ShoppingCartService cartService;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?> saveOrUpdate(@RequestBody ShoppingCartDetail shoppingCartDetail) {
        try {
            ShoppingCartDetail check = cartDetailService.saveOrUpDate(shoppingCartDetail);
            if (check == null) {
                return ResponseEntity.badRequest().body("Failed !!");
            }
            return ResponseEntity.ok().body(check);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        try {
            return ResponseEntity.ok().body(cartDetailService.getById(id));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getByCartId/{cartId}")
    public ResponseEntity<?> getCartItemByCart(@PathVariable("cartId") int cartId) {
        try {
            ShoppingCart shoppingCart = cartService.getByIdCart(cartId);
            if (shoppingCart == null) {
                return ResponseEntity.ok().body(cartId + " not found !!");
            }
            List<ShoppingCartDetail> cartItems = cartDetailService.getByCart(shoppingCart);
            if (cartItems.isEmpty()) {
                return ResponseEntity.ok().body("cart is null !!");
            }
            return ResponseEntity.ok().body(cartItems);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        try {
            if (Boolean.FALSE.equals(cartDetailService.remove(id))) {
                return ResponseEntity.badRequest().body("Failed!!");
            }
            return ResponseEntity.ok().body("Success!!");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }
}
