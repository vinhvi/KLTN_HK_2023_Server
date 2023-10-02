package com.example.demo.service;

import com.example.demo.entity.Role;

import java.util.List;

public interface RoleService {
    Role createRole(Role role);

    List<Role> getAll();

    List<Role> getByName(String name);

    Role getById(int id);
}
