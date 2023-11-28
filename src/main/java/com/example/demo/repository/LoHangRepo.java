package com.example.demo.repository;

import com.example.demo.entity.ImportOrder;
import com.example.demo.entity.ImportOrderDetail;
import com.example.demo.entity.LoHang;
import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoHangRepo extends JpaRepository<LoHang, String> {
    LoHang findLoHangById(String id);

    List<LoHang> findLoHangByProduct(Product product);
}
