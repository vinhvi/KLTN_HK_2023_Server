package com.example.demo.repository;

import com.example.demo.entity.ImportOrder;
import com.example.demo.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ImportOrderRepo extends JpaRepository<ImportOrder,String> {

    ImportOrder findImportOrderById(String id);
    List<ImportOrder> getImportOrderByDate(Date date);
    List<ImportOrder> getImportOrderBySupplier(Supplier supplier);
    @Query("SELECT o FROM ImportOrder o WHERE month(o.date) = :month")
    List<ImportOrder> getImportOrderByMoth(int month);
}
