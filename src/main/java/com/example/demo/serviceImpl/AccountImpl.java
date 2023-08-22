package com.example.demo.serviceImpl;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepo;
import com.example.demo.service.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountImpl implements AccountService {
    private final AccountRepo accountRepo;

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
        return accountRepo.save(account);
    }

    @Override
    public String login(Account account) {
        Account accountCheck = accountRepo.findAccountByEmail(account.getEmail());
        if ( accountCheck == null || !accountCheck.getPassWordA().equals(account.getPassWordA())){
            return "Sai tài khoản hoặc tài khoản !!";
        }
        return "Thành Công !!";
    }
}
