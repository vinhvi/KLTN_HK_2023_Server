package com.example.demo.controller;

import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<Role> create(@RequestBody Role role) {
        return ResponseEntity.ok().body(roleService.createRole(role));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getById(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(roleService.getById(id));
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name) {
        Role role = roleService.getByName(name);
        if (role == null) {
            return ResponseEntity.badRequest().body(name + " not found!");
        }
        return ResponseEntity.ok().body(role);
    }

    @GetMapping("/getList")
    public ResponseEntity<List<Role>> getAll() {
        return ResponseEntity.ok().body(roleService.getAll());
    }
}
