package com.example.demo.controller;

import com.example.demo.entity.ImageProduct;
import com.example.demo.entity.Product;
import com.example.demo.service.CloudinaryService;
import com.example.demo.service.ImageProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/imageProducts")
public class ImageProductController {
    private final ImageProductService imageProductService;
    private final CloudinaryService cloudinaryService;

    @PostMapping("/saveOrUpdate/{productId}")
    public ResponseEntity<?> saveOrUpdate(@RequestBody MultipartFile multipartFile, @PathVariable("productId") String productId) throws IOException {
        try {
            BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
            if (bi == null) {
                return ResponseEntity.badRequest().body("Error !!");
            }
            Map result = cloudinaryService.upload(multipartFile);
            ImageProduct imageProduct = new ImageProduct();
            imageProduct.setImageLink((String) result.get("url"));
            imageProduct.setIdCloud((String) result.get("public_id"));
            // Sử dụng DateTimeFormatter để chuyển đổi chuỗi thành đối tượng Instant
            DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
            Instant instant = Instant.from(formatter.parse((String) result.get("created_at")));
            Date date = Date.from(instant);
            imageProduct.setDate(date);
            imageProduct.setType((String) result.get("format"));
            int bytes = (int) result.get("bytes");
            double size = (double) bytes / 1024;
            String sizeFormat = String.format("%.3f", size);
            imageProduct.setSize(sizeFormat + "KB");
            Product product = new Product();
            product.setId(productId);
            imageProduct.setProduct(product);
            return ResponseEntity.ok().body(imageProductService.addImage(imageProduct));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }

    }

    @PostMapping("/saveOrUpdateForList/{productId}")
    public ResponseEntity<?> saveOrUpdateForList(@RequestBody List<MultipartFile> multipartFiles, @PathVariable("productId") String productId) throws IOException {
        try {
            List<ImageProduct> imageProducts = new ArrayList<>();
            for (MultipartFile multipartFile : multipartFiles) {
                BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
                if (bi == null) {
                    return ResponseEntity.badRequest().body("Error !!");
                }
                Map result = cloudinaryService.upload(multipartFile);
                ImageProduct imageProduct = new ImageProduct();
                imageProduct.setImageLink((String) result.get("url"));
                imageProduct.setIdCloud((String) result.get("public_id"));
                // Sử dụng DateTimeFormatter để chuyển đổi chuỗi thành đối tượng Instant
                DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
                Instant instant = Instant.from(formatter.parse((String) result.get("created_at")));
                Date date = Date.from(instant);
                imageProduct.setDate(date);
                imageProduct.setType((String) result.get("format"));
                int bytes = (int) result.get("bytes");
                double size = (double) bytes / 1024;
                String sizeFormat = String.format("%.3f", size);
                imageProduct.setSize(sizeFormat + "KB");
                Product product = new Product();
                product.setId(productId);
                imageProduct.setProduct(product);
                imageProducts.add(imageProductService.addImage(imageProduct));
            }
            return ResponseEntity.ok().body(imageProducts);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) throws IOException {
        try {
            if (!imageProductService.check(id)) {
                return ResponseEntity.badRequest().body("ImageProduct not found !!");
            }
            ImageProduct imageProduct = imageProductService.getById(id);
            Map result = cloudinaryService.delete(imageProduct.getIdCloud());
            imageProductService.remove(id);
            return ResponseEntity.ok().body(result);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("There is an exception when execute !! --> " + exception);
        }

    }
}
