package com.example.demo.service;

import com.example.demo.DataBean.CustomerDataBean;
import com.example.demo.DataBean.EmployeeDataBean;
import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Employee;

import java.util.Optional;

public interface AccountService {
    Optional<Account> getByEmail(String email);

    Account getById(Integer id);

    Account register(Account account);

    String login(Account account);

    CustomerDataBean customerLogin(String token, Customer customer);
    EmployeeDataBean employeeLogin(String token, Employee employee);
}
