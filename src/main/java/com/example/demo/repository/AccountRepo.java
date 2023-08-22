package com.example.demo.repository;

import com.example.demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AccountRepo extends JpaRepository<Account,Integer> {
    Optional<Account> findByEmail(String email);
    Account findAccountByEmail(String email);
    Account findAccountById(Integer id);
}
