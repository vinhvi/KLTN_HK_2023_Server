package com.example.demo.repository;

import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee,String> {
    Employee findEmployeeById(String id);
    Employee findEmployeeByEmail(String email);
}
