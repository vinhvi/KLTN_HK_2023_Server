package com.example.demo.serviceImpl;

import com.example.demo.entity.Brand;
import com.example.demo.repository.BrandRepo;
import com.example.demo.service.BrandService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BrandImpl implements BrandService {

    private final BrandRepo brandRepo;

    @Override
    public Brand saveOrUpdate(Brand brand) {
        return brandRepo.save(brand);
    }

    @Override
    public Brand getById(int id) {
        return brandRepo.findBrandById(id);
    }

    @Override
    public Brand getByName(String name) {
        return brandRepo.findBrandByName(name);
    }

    @Override
    public List<Brand> getAll() {
        return brandRepo.findAll();
    }
}
