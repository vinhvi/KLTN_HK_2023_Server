package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/accounts")
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody Account account) {
        try {
            Account accountUpdate = accountService.getByEmail2(account.getEmail());
            if (accountUpdate == null) {
                return ResponseEntity.badRequest().body(account.getId() + " not found!");
            }
            accountUpdate.setEnable(account.isEnable());
            return ResponseEntity.ok().body(accountService.saveOrUpdate(accountUpdate));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            List<Account> accounts = accountService.getAll();
            if (accounts.isEmpty()) {
                return ResponseEntity.badRequest().body(" not found!");
            }
            return ResponseEntity.ok().body(accounts);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }
    @GetMapping("/getById/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable("email") String email) {
        try {
            Account account = accountService.getByEmail2(email);
            if (account == null) {
                return ResponseEntity.badRequest().body(email + " not found!");
            }
            return ResponseEntity.ok().body(account);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

}
