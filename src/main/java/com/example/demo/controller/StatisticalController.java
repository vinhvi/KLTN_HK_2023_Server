package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.Product;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/shoppingCarts")
public class StatisticalController {
    private final OrderService orderService;

    public ResponseEntity<?> staticalForMonth(@PathVariable("date") String date){
        try {
            int month = 0;
            int year = 0;
            String[] parts = date.split("-");
            if (parts.length == 2) {
                month = Integer.parseInt(parts[0]);
                year = Integer.parseInt(parts[1]);
            } else {
                return ResponseEntity.badRequest().body("Invalid input format !!");
            }
            List<Order> orderList = orderService.getByDate(month, year);

            if (orderList.isEmpty()) {
                return ResponseEntity.badRequest().body("not found!!");
            }
            for (Order order:orderList) {
                for (OrderDetail orderDetail:order.getOrderDetails()) {
                    Product product = orderDetail.getProduct();

                }
            }
            
            return ResponseEntity.badRequest().body("not found!!");

        }catch (Exception exception){
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }
}
