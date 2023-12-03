package com.example.demo.repository;

import com.example.demo.entity.ImportOrder;
import com.example.demo.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ImportOrderRepo extends JpaRepository<ImportOrder,String> {

    ImportOrder findImportOrderById(String id);
    List<ImportOrder> findImportOrderByDateBetween(Date start, Date end);
}
