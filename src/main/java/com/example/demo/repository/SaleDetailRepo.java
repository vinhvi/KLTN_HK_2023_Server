package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleDetailRepo extends JpaRepository<SaleDetail,Integer> {
    SaleDetail findSaleDetailById(int id);
    SaleDetail findSaleDetailByEnableAndProduct(int enable, Product product);

}
