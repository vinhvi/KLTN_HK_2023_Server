package com.example.demo.serviceImpl;

import com.example.demo.config.JwtService;
import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepo;
import com.example.demo.service.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountImpl implements AccountService {
    private final AccountRepo accountRepo;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    @Override
    public Optional<Account> getByEmail(String email) {
        return accountRepo.findByEmail(email);
    }

    @Override
    public Account getById(Integer id) {
        return accountRepo.findAccountById(id);
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
    public String login(Account account) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword()));
        var user = accountRepo.findByEmail(account.getEmail()).orElseThrow();
        return jwtService.generateToken(user);
    }
}
