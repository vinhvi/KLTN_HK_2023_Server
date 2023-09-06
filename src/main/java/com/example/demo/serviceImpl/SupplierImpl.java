package com.example.demo.serviceImpl;

import com.example.demo.entity.Supplier;
import com.example.demo.repository.SupplierRepo;
import com.example.demo.service.SupplierService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SupplierImpl implements SupplierService {
    private final SupplierRepo supplierRepo;

    @Override
    public Supplier saveOrUpdate(Supplier supplier) {
        return supplierRepo.save(supplier);
    }

    @Override
    public Supplier getById(int id) {
        return supplierRepo.findSupplierById(id);
    }

    @Override
    public Supplier getByName(String name) {
        return supplierRepo.findSupplierByName(name);
    }

    @Override
    public List<Supplier> getSuppliers() {
        return supplierRepo.findAll();
    }
}
