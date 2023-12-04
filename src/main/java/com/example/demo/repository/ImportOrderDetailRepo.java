package com.example.demo.repository;

import com.example.demo.entity.ImportOrderDetail;
import com.example.demo.entity.LoHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportOrderDetailRepo extends JpaRepository<ImportOrderDetail,Integer> {
    ImportOrderDetail findImportOrderDetailById(int id);

    ImportOrderDetail findImportOrderDetailByLoHang(LoHang loHang);
}
