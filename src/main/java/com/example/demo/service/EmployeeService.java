package com.example.demo.service;

import com.example.demo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Employee getById(String id);
    Employee getByEmail(String email);
    List<Employee> getAll();
    String generateId();
    Employee getByEmailOrPhone(String key);
}
