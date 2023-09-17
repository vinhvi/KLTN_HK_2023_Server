package com.example.demo.serviceImpl;

import com.example.demo.entity.Image;
import com.example.demo.repository.ImageRepo;
import com.example.demo.service.ImageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ImageServiceImpl implements ImageService {
    private final ImageRepo imageRepo;

    @Override
    public Image addImage(Image image) {
        return imageRepo.save(image);
    }

    @Override
    public boolean check(String id) {
        return imageRepo.existsById(id);
    }

    @Override
    public Boolean remove(String id) {
        try {
            Image image = imageRepo.findImageById(id);
            imageRepo.delete(image);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Image getById(String id) {
        return imageRepo.findImageById(id);
    }
}
