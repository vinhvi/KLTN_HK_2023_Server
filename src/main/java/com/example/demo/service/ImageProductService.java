package com.example.demo.service;

import com.example.demo.entity.ImageProduct;

public interface ImageProductService {
    ImageProduct addImage(ImageProduct imageProduct);
    boolean check(String id);
    Boolean remove(String id);
    ImageProduct getById(String id);

}
