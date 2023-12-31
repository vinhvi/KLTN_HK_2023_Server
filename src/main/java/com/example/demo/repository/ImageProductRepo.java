package com.example.demo.repository;

import com.example.demo.entity.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageProductRepo extends JpaRepository<ImageProduct,Integer> {
    ImageProduct findImageById(int id);

}
