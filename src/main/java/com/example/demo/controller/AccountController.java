package com.example.demo.controller;

import com.example.demo.DataBean.AccountChange;
import com.example.demo.entity.Account;
import com.example.demo.entity.Role;
import com.example.demo.service.AccountService;
import com.example.demo.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/accounts")
public class AccountController {
    private final AccountService accountService;
    private final RoleService roleService;

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

    @PostMapping("/changePasswrod")
    public ResponseEntity<?> changePassword(@RequestBody AccountChange accountChange) {
        try {
            int check = accountService.changePassword(accountChange);
            if (check == 0) {
                return ResponseEntity.badRequest().body("Thất bại!!");
            }
            return ResponseEntity.ok().body("Thành công!!");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @PostMapping("/addRoleToAccount/{idRole}")
    public ResponseEntity<?> addRole(@RequestBody Account account, @PathVariable("idRole") int idRole) {
        try {
            Account accountAddRole = accountService.getById(account.getId());
            if (accountAddRole == null) {
                return ResponseEntity.badRequest().body(account.getId() + " not found!!");
            }
            Role role = roleService.getById(idRole);
            if (role == null) {
                return ResponseEntity.badRequest().body(account.getId() + " not found!!");
            }
            return ResponseEntity.ok().body(accountService.addRoleToAccount(account, role));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }

    @PostMapping("/removeRoleToAccount/{idRole}")
    public ResponseEntity<?> removeRole(@RequestBody Account account, @PathVariable("idRole") int idRole) {
        try {
            Account accountAddRole = accountService.getById(account.getId());
            if (accountAddRole == null) {
                return ResponseEntity.badRequest().body(account.getId() + " not found!!");
            }
            Role role = roleService.getById(idRole);
            if (role == null) {
                return ResponseEntity.badRequest().body(account.getId() + " not found!!");
            }
            return ResponseEntity.ok().body(accountService.removeRoleFromAccount(account, role));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }
    }
}
