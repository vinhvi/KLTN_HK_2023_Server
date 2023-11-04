package com.example.demo.service;

import com.example.demo.entity.ImportOrder;

import java.util.List;

public interface ImportOrderService {
    ImportOrder saveOrUpdate(ImportOrder importOrder);
    List<ImportOrder> getAll();
    ImportOrder getById(String id);
    String randomId();
}
