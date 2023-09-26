package com.example.demo.serviceImpl;

import com.example.demo.entity.Avatar;
import com.example.demo.entity.Employee;
import com.example.demo.entity.ImageProduct;
import com.example.demo.repository.EmployeeRepo;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.ImageProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EmployeeImpl implements EmployeeService {
    private EmployeeRepo employeeRepo;

    @Override
    public Employee createEmployee(Employee employee) {
        //set image default in database for customer if image null
        if (employee.getAvatar() == null) {
            Avatar avatar = new Avatar();
            avatar.setId("default");
            employee.setAvatar(avatar);
        }
        return employeeRepo.save(employee);
    }

    @Override
    public Employee getOneEmployee(Employee employee) {
        return null;
    }

    @Override
    public Employee getById(String id) {
        return null;
    }

    @Override
    public Employee getByEmail(String email) {
        return null;
    }

    @Override
    public List<Employee> getAll() {
        return null;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return null;
    }

    @Override
    public String generateId() {
        return null;
    }
}
