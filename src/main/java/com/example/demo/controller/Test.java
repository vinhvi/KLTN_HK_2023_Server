package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1")
public class Test {
    @GetMapping("/test")
    public ResponseEntity<?> testRequest() {
        return ResponseEntity.ok().body("Hello word !");
    }
}
