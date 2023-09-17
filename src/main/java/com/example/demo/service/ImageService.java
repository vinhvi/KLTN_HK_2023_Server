package com.example.demo.service;

import com.example.demo.entity.Image;

public interface ImageService {
    Image addImage(Image image);
    boolean check(String id);
    Boolean remove(String id);
    Image getById(String id);

}
