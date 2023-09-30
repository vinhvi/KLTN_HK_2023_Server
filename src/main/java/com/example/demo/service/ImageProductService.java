package com.example.demo.service;

import com.example.demo.entity.ImageProduct;

public interface ImageProductService {
    ImageProduct addImage(ImageProduct imageProduct);
    boolean check(int id);
    Boolean remove(int id);
    ImageProduct getById(int id);

}
