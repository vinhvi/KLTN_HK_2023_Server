package com.example.demo.repository;

import com.example.demo.entity.PriceList;
import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceListRepo extends JpaRepository<PriceList,Integer> {
    PriceList findPriceListById(int id);
    List<PriceList> findPriceListByProduct(Product product);


}
