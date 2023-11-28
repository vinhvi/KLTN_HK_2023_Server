package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.entity.SaleDetail;

public interface SaleDetailService {
    SaleDetail saveOrUpdate(SaleDetail saleDetail);

    SaleDetail getById(int id);
    SaleDetail getByProductAndStatus(int status, Product product);
}
