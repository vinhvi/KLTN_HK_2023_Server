package com.example.demo.serviceImpl;

import com.example.demo.DataBean.CustomerDataBean;
import com.example.demo.DataBean.EmployeeDataBean;
import com.example.demo.config.JwtService;
import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Employee;
import com.example.demo.repository.AccountRepo;
import com.example.demo.service.AccountService;
import com.example.demo.service.ShoppingCartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountImpl implements AccountService {
    private final AccountRepo accountRepo;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final ShoppingCartService shoppingCartService;
    private final JwtService jwtService;

    @Override
    public Optional<Account> getByEmail(String email) {
        return accountRepo.findByEmail(email);
    }

    @Override
    public Account saveOrUpdate(Account account) {
        return accountRepo.save(account);
    }

    @Override
    public Account getById(Integer id) {
        return accountRepo.findAccountById(id);
    }

    @Override
    public Account getByEmail2(String email) {
        return accountRepo.findAccountByEmail(email);
    }

    @Override
    public Account register(Account account) {

        String password = account.getPassWordA();
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        String encodedPassword = passwordEncoder.encode(password);
        account.setPassWordA(encodedPassword);
        return accountRepo.save(account);
    }

    @Override
    public List<Account> getAll() {
        return accountRepo.findAll();
    }

    @Override
    public String login(Account account) {
        String token = "";
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword()));
            var user = accountRepo.findByEmail(account.getEmail()).orElseThrow();
            return jwtService.generateToken(user);
        } catch (Exception exception) {
            return token;
        }

    }

    @Override
    public CustomerDataBean customerLogin(String token, Customer customer) {
        CustomerDataBean customerDataBean = new CustomerDataBean();
        customerDataBean.setId(customer.getId());
        customerDataBean.setEmail(customer.getEmail());
        customerDataBean.setCustomerType(customer.getCustomerType());
        customerDataBean.setAvatar(customer.getAvatar());
        customerDataBean.setAccount(customer.getAccount());
        customerDataBean.setAddress(customer.getAddress());
        customerDataBean.setSex(customer.getSex());
        customerDataBean.setPhone(customer.getPhone());
        customerDataBean.setToken(token);
        customerDataBean.setFirstName(customer.getFirstName());
        customerDataBean.setLastName(customer.getLastName());
        customerDataBean.setShoppingCart(shoppingCartService.getByCustomer(customer));
        return customerDataBean;
    }

    @Override
    public EmployeeDataBean employeeLogin(String token, Employee employee) {
        EmployeeDataBean employeeDataBean = new EmployeeDataBean();
        employeeDataBean.setId(employee.getId());
        employeeDataBean.setEmail(employee.getEmail());
        employeeDataBean.setWorkdate(employee.getWorkdate());
        employeeDataBean.setAvatar(employee.getAvatar());
        employeeDataBean.setAccount(employee.getAccount());
        employeeDataBean.setAddress(employee.getAddress());
        employeeDataBean.setSex(employee.getSex());
        employeeDataBean.setPhone(employee.getPhone());
        employeeDataBean.setToken(token);
        employeeDataBean.setFirstName(employee.getFirstName());
        employeeDataBean.setLastName(employee.getLastName());
        return employeeDataBean;
    }
}
