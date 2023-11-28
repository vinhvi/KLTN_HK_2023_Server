package com.example.demo.serviceImpl;

import com.example.demo.entity.Product;
import com.example.demo.entity.SaleDetail;
import com.example.demo.repository.SaleDetailRepo;
import com.example.demo.service.SaleDetailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SaleDetailImpl implements SaleDetailService {
    private final SaleDetailRepo saleDetailRepo;

    @Override
    public SaleDetail saveOrUpdate(SaleDetail saleDetail) {
        return saleDetailRepo.save(saleDetail);
    }

    @Override
    public SaleDetail getById(int id) {
        return saleDetailRepo.findSaleDetailById(id);
    }

    @Override
    public SaleDetail getByProductAndStatus(int status, Product product) {
        return saleDetailRepo.findSaleDetailByEnableAndProduct(status, product);
    }
}
