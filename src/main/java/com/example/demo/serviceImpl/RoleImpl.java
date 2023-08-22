package com.example.demo.serviceImpl;


import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepo;
import com.example.demo.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoleImpl implements RoleService {
    private final RoleRepo roleRepo;

    @Override
    public Role createRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public List<Role> getAll() {
        return roleRepo.findAll();
    }

    @Override
    public Role getByName(String name) {
        return roleRepo.findRoleByName(name);
    }

    @Override
    public Role getById(int id) {
        return roleRepo.findRoleById(id);
    }
}
