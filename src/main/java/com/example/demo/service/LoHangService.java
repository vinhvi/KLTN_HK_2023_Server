package com.example.demo.service;

import com.example.demo.entity.LoHang;
import com.example.demo.entity.Product;

import java.util.List;

public interface LoHangService {
    LoHang saveOrUpdate(LoHang loHang);

    LoHang getById(String iD);

    List<LoHang> getAll();

    List<LoHang> getByProduct(Product product);

    String randomIDLH();

    void delete(LoHang loHang);
}
