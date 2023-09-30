package com.example.demo.serviceImpl;

import com.example.demo.entity.ImageProduct;
import com.example.demo.repository.ImageProductRepo;
import com.example.demo.service.ImageProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ImageProductServiceImpl implements ImageProductService {
    private final ImageProductRepo imageProductRepo;

    @Override
    public ImageProduct addImage(ImageProduct imageProduct) {
        return imageProductRepo.save(imageProduct);
    }

    @Override
    public boolean check(int id) {
        return imageProductRepo.existsById(id);
    }

    @Override
    public Boolean remove(int id) {
        try {
            ImageProduct imageProduct = imageProductRepo.findImageById(id);
            imageProductRepo.delete(imageProduct);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public ImageProduct getById(int id) {
        return imageProductRepo.findImageById(id);
    }
}
