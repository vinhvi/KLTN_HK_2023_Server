package com.example.demo.service;

import com.example.demo.entity.PriceList;
import com.example.demo.entity.Product;

import java.util.List;

public interface PriceListService {
    PriceList saveOrUpdate(PriceList priceList);
    PriceList getById(int id);
    List<PriceList> getAll();
    List<PriceList> getByProduct(Product product);
}
