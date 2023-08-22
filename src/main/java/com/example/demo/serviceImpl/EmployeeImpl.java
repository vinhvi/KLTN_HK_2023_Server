package com.example.demo.serviceImpl;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Image;
import com.example.demo.repository.EmployeeRepo;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.ImageService;
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
    private final ImageService imageService;

    @Override
    public Employee createEmployee(Employee employee) {
        Image imageDefault = new Image();
        //set image default in database for customer if image null
        if (employee.getImage() == null) {
            employee.setImage("https://res.cloudinary.com/dv329zg5e/image/upload/v1692689754/user_default_txm2pe.png");
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
