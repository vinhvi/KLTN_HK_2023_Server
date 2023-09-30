package com.example.demo.controller;

import com.example.demo.DataBean.CustomerDataBean;
import com.example.demo.DataBean.EmployeeDataBean;
import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Employee;
import com.example.demo.service.AccountService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AccountService accountService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Account account) {
        try {
            if (accountService.getByEmail(account.getEmail()).isPresent()) {
                return ResponseEntity.ok().body("email is ready in database");
            } else {
                Account registeredAccount = accountService.register(account);
                if (registeredAccount == null) {
                    return ResponseEntity.badRequest().body("Error !!");
                }
                return ResponseEntity.ok().body(registeredAccount);
            }
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute!!" + exception);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        try {
            String token = accountService.login(account);
            if (token == null) {
                return ResponseEntity.ok().body("email or incorrect password!!");
            }
            Customer customer = customerService.getByEmail(account.getEmail());
            if (customer != null) {
                CustomerDataBean customerDataBean = accountService.customerLogin(token, customer);
                return ResponseEntity.ok().body(customerDataBean);
            }
            Employee employee = employeeService.getByEmail(account.getEmail());
            EmployeeDataBean employeeDataBean = accountService.employeeLogin(token, employee);
            return ResponseEntity.ok().body(employeeDataBean);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute!!" + exception);
        }
    }
}
