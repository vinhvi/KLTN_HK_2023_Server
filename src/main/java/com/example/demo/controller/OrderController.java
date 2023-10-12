package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final CustomerService customerService;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?> saveOrUpdate(@RequestBody Order order) {
        try {
            return ResponseEntity.ok().body(orderService.saveOrUpdate(order));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getAllOrder")
    public ResponseEntity<?> getAllOrder() {
        try {
            List<Order> orderList = orderService.getAll();
            if (orderList.isEmpty()) {
                return ResponseEntity.badRequest().body("not found!!");
            }
            return ResponseEntity.ok().body(orderList);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getOrderById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String orderId) {
        try {
            return ResponseEntity.ok().body(orderService.getOrderById(orderId));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/randomId")
    public ResponseEntity<?> randomId() {
        try {
            return ResponseEntity.ok().body(orderService.randomOrderId());
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getByDate")
    public ResponseEntity<?> getOrderByDate(@RequestBody Date date) {
        try {
            List<Order> orderList = orderService.getByDate(date);
            if (orderList.isEmpty()) {
                return ResponseEntity.badRequest().body("not found!!");
            }
            return ResponseEntity.ok().body(orderList);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getByCustomer/{key}")
    public ResponseEntity<?> getOrderByCustomer(@PathVariable("key") String key) {
        try {
            Customer customer = customerService.getByPhoneOrEmail(key);
            if (customer != null) {
                List<Order> orderList = orderService.getOrderByCustomer(customer);
                return ResponseEntity.ok().body(orderList);
            }
            Customer customer1 = customerService.getById(key);
            if (customer1 != null) {
                List<Order> orderList = orderService.getOrderByCustomer(customer1);
                return ResponseEntity.ok().body(orderList);
            }
            return ResponseEntity.badRequest().body("customer " + key + " not found!!");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }
}
