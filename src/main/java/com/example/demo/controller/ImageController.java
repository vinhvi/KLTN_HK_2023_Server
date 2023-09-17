package com.example.demo.controller;

import com.example.demo.entity.Image;
import com.example.demo.entity.Product;
import com.example.demo.service.CloudinaryService;
import com.example.demo.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/images")
public class ImageController {
    private final ImageService imageService;
    private final CloudinaryService cloudinaryService;

    @PostMapping("/saveOrUpdate/{productId}")
    public ResponseEntity<?> saveOrUpdate(@RequestBody MultipartFile multipartFile, @PathVariable("productId") String productId) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (bi == null) {
            return ResponseEntity.badRequest().body("Error !!");
        }
        Map result = cloudinaryService.upload(multipartFile);
        Image image = new Image();
        image.setImageLink((String) result.get("url"));
        image.setId((String) result.get("public_id"));
        Product product = new Product();
        product.setId(productId);
        image.setProduct(product);
        return ResponseEntity.ok().body(imageService.addImage(image));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) throws IOException {
        if (!imageService.check(id)) {
            return ResponseEntity.badRequest().body("Image not found !!");
        }
        Image image = imageService.getById(id);
        Map result = cloudinaryService.delete(image.getId());
        imageService.remove(id);
        return ResponseEntity.ok().body(result);
    }
}
