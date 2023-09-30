package com.example.demo.service;

import com.example.demo.entity.Brand;

import java.util.List;

public interface BrandService {
    Brand saveOrUpdate(Brand brand);
    Brand getById(int id);
    Brand getByName(String name);
    List<Brand> getAll();
}
