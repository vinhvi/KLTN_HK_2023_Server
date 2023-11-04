package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/createOrUpdate")
    public ResponseEntity<?> create(@RequestBody Employee employee) {
        try {
            return ResponseEntity.ok().body(employeeService.createEmployee(employee));
        }catch (Exception exception){
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllEmployee(){
        try {
            List<Employee> employees = employeeService.getAll();
            return ResponseEntity.ok().body(Objects.requireNonNullElse(employees, "There are no employees in the database yet"));
        }catch (Exception exception){
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }
    @GetMapping("/getByEmailOrPhone/{key}")
    public ResponseEntity<?> getByEmailOrPhone(@PathVariable("key") String key){
        try {
            Employee employee = employeeService.getByEmailOrPhone(key);
            if (employee == null){
                return ResponseEntity.badRequest().body(key +  " not found!!");
            }
            return ResponseEntity.ok().body(employee);
        }catch (Exception exception){
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

}
