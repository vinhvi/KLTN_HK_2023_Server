package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.service.OrderDetailService;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/orderDetails")
public class OrderDetailController {
    private final OrderDetailService orderDetailService;
    private final OrderService orderService;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?> saveOrUpdate(@RequestBody OrderDetail orderDetail) {
        try {
            return ResponseEntity.ok().body(orderDetailService.saveOrUpdate(orderDetail));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        try {
            OrderDetail orderDetail = orderDetailService.getById(id);
            if (orderDetail != null) {
                return ResponseEntity.ok().body(orderDetail);
            }
            return ResponseEntity.badRequest().body("OrderDetail Id: " + id + " not found!!");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getByOrder/{orderId}")
    public ResponseEntity<?> getByOrder(@PathVariable("orderId") String orderId) {
        try {
            Order order = orderService.getById(orderId);
            if (order != null) {
                List<OrderDetail> orderDetails = orderDetailService.getByOrder(order);
                return ResponseEntity.ok().body(orderDetails);
            }
            return ResponseEntity.badRequest().body("Order Id: " + orderId + " not found!!");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        try {
            OrderDetail orderDetail = orderDetailService.getById(id);
            if (orderDetail != null) {
                orderDetailService.deleteOrderDetail(orderDetail);
                return ResponseEntity.ok().body("Success!!");
            }
            return ResponseEntity.badRequest().body("OrderDetail Id: " + id + " not found!!");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }
}
