package com.example.demo.serviceImpl;

import com.example.demo.entity.Account;
import com.example.demo.entity.Avatar;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Role;
import com.example.demo.repository.EmployeeRepo;
import com.example.demo.service.AccountService;
import com.example.demo.service.AvatarService;
import com.example.demo.service.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EmployeeImpl implements EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final AccountService accountService;
    private final AvatarService avatarService;

    @Override
    public Employee createEmployee(Employee employee) {
        //set image default in database for employee if create new employee
        if (employee.getId() == null) {
            employee.setId(generateId());
            Avatar avatar = new Avatar();
            avatar.setIdCloud("default");
            avatar.setImageLink("https://res.cloudinary.com/dv329zg5e/image/upload/v1692689754/user_default_txm2pe.png");
            employee.setAvatar(avatarService.addAvatar(avatar));

            Account account = new Account();
            account.setEmail(employee.getEmail());
            account.setPassWordA("123");
            Role role = new Role();
            role.setId(2);
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            account.setRoles(roles);
            employee.setAccount(accountService.register(account));
        }
        return employeeRepo.save(employee);
    }


    @Override
    public Employee getById(String id) {
        return employeeRepo.findEmployeeById(id);
    }

    @Override
    public Employee getByEmail(String email) {
        return employeeRepo.findEmployeeByEmail(email);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepo.findAll();
    }


    @Override
    public String generateId() {
        Random random = new Random();
        String id = "";
        boolean check = false;
        int attempts = 0;
        int maxAttempts = 10000; // set a maximum number of attempts to prevent infinite loop
        while (!check && attempts < maxAttempts) {
            int number = random.nextInt(10000);
            id = "NV" + String.format("%04d", number);
            if (employeeRepo.findEmployeeById(id) == null) {
                check = true;
            }
            attempts++;
        }
        if (!check) {
            throw new RuntimeException("Unable to generate a unique ID after " + maxAttempts + " attempts.");
        }
        return id;
    }

    @Override
    public Employee getByEmailOrPhone(String key) {
        return employeeRepo.findEmployeeByEmailOrPhone(key,key);
    }
}
