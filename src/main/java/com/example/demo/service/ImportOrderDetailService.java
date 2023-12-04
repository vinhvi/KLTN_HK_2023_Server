package com.example.demo.service;

import com.example.demo.entity.ImportOrderDetail;
import com.example.demo.entity.LoHang;

import java.util.List;

public interface ImportOrderDetailService {
    ImportOrderDetail saveOrUpdate(ImportOrderDetail importOrderDetail);
    ImportOrderDetail getById(int id);
    void deleteById(ImportOrderDetail importOrderDetail);
    ImportOrderDetail getByLH(LoHang loHang);
}
