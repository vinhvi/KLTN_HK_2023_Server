package com.example.demo.service;

import com.example.demo.entity.Account;

import java.util.Optional;

public interface AccountService {
    Optional<Account> getByEmail(String email);

    Account getById(Integer id);

    Account register(Account account);

    String login(Account account);
}
