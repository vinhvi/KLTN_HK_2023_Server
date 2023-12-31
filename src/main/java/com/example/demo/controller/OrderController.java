package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Order;
import com.example.demo.service.CustomerService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;

    @PostMapping("/saveOrUpdate/{idCart}")
    public ResponseEntity<?> saveOrUpdate(@PathVariable("idCart") int idCart, @RequestBody Order order) {
        try {
            Order orderSave = orderService.saveOrUpdate(idCart, order);
            if (orderSave == null) {
                return ResponseEntity.badRequest().body("Error while save Order because quantity < 0 !!");
            }
            return ResponseEntity.ok().body(orderSave);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }
    @PostMapping("/createNow")
    public ResponseEntity<?> createNow(@RequestBody Order order) {
        try {
            Order orderSave = orderService.createNow(order);
            if (orderSave == null) {
                return ResponseEntity.badRequest().body("Error while save Order because quantity < 0 !!");
            }
            return ResponseEntity.ok().body(orderSave);
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
    @GetMapping("/getListUpdate")
    public  ResponseEntity<?> getListUpdate(){
        try {
            List<Order> orderList = orderService.listConfirm();
            if (orderList==null){
                return ResponseEntity.badRequest().body("null");
            }
            return ResponseEntity.ok().body(orderList);
        }catch (Exception exception){
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getByDate/{date}")
    public ResponseEntity<?> getOrderByDate(@PathVariable("date") String date) {
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
            List<Order> orderList = orderService.getByDateBetween(month, year);
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

    @PostMapping("/updateStatus/{idEmployee}")
    public ResponseEntity<?> updateStatus(@PathVariable("idEmployee") String idEmployee, @RequestBody List<Order> orderList) {
        try {
            Employee employee = employeeService.getById(idEmployee);
            List<Order> orders = orderService.update(employee, orderList);
            if (orders.isEmpty()) {
                return ResponseEntity.badRequest().body("error");
            }
            return ResponseEntity.ok().body("success!");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }
}
