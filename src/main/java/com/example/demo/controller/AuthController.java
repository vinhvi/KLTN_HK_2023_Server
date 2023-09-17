package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Account account) {
        if (accountService.getByEmail(account.getEmail()).isPresent()) {
            return ResponseEntity.ok().body("email is ready in database");
        } else {
            Account registeredAccount = accountService.register(account);
            if (registeredAccount == null) {
                return ResponseEntity.badRequest().body("Error !!");
            }
            return ResponseEntity.ok().body(registeredAccount);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        String token = accountService.login(account);
        return ResponseEntity.ok().body(Objects.requireNonNullElse(token, "Tài khoản hoặc mật khẩu khôn đúng !!"));
    }
}
