package com.example.demo.service;

import com.example.demo.DataBean.AccountChange;
import com.example.demo.DataBean.CustomerDataBean;
import com.example.demo.DataBean.EmployeeDataBean;
import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Optional<Account> getByEmail(String email);
    Account saveOrUpdate(Account account);
    Account getById(Integer id);
    Account getByEmail2(String email);
    Account register(Account account);
    List<Account> getAll();
    String login(Account account);
    int changePassword(AccountChange account);
    String forgotPassword(String email);
    CustomerDataBean customerLogin(String token, Customer customer);
    EmployeeDataBean employeeLogin(String token, Employee employee);
}
