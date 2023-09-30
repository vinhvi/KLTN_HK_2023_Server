package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/createOrUpdate")
    public ResponseEntity<?> createOrUpdate(@RequestBody Customer customer) {
        try {
            return ResponseEntity.ok().body(customerService.addCustomer(customer));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute!! --> " + exception);
        }
    }

    @GetMapping("/randomId")
    public ResponseEntity<String> randomIdCustomer() {
        try {
            return ResponseEntity.ok().body(customerService.randomId());

        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! -->" + exception);
        }

    }

    @GetMapping("/getListCustomer")
    public ResponseEntity<?> getListCustomer() {
        try {
            List<Customer> customerList = customerService.getAll();
            if (!customerList.isEmpty()) {
                return ResponseEntity.ok().body(customerList);
            } else {
                return ResponseEntity.ok().body("null !!");
            }
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! -->" + exception);
        }
    }

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable("email") String email) {
        try {
            Customer customer = customerService.getByEmail(email);
            if (customer != null) {
                return ResponseEntity.ok(customer);
            }
            return ResponseEntity.ok().body(email + " is not found !!");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }

    }

    @GetMapping("/getByPhone/{phone}")
    public ResponseEntity<?> getByPhone(@PathVariable("phone") String phone) {
        try {
            Customer customer = customerService.getByPhone(phone);
            if (customer != null) {
                return ResponseEntity.ok(customer);
            }
            return ResponseEntity.ok().body(phone + " is not found !!");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }

    }
}
