package com.example.demo.controller;

import com.example.demo.entity.Image;
import com.example.demo.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/images")
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?> saveOrUpdate(@RequestBody Image image) {
        Image check = imageService.addImage(image);
        if (check == null) {
            return ResponseEntity.badRequest().body("Failed !!");
        }
        return ResponseEntity.ok().body(check);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (Boolean.FALSE.equals(imageService.remove(id))) {
            return ResponseEntity.badRequest().body("Failed!!");
        }
        return ResponseEntity.ok().body("Success!!");
    }
}
