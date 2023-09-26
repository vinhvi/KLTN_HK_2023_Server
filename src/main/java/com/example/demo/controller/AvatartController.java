package com.example.demo.controller;

import com.example.demo.entity.Avatar;
import com.example.demo.entity.ImageProduct;
import com.example.demo.entity.Product;
import com.example.demo.service.AvatarService;
import com.example.demo.service.CloudinaryService;
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
@RequestMapping("api/v1/avatars")
public class AvatartController {
    private final AvatarService avatarService;
    private final CloudinaryService cloudinaryService;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?> saveOrUpdate(@RequestBody MultipartFile multipartFile) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (bi == null) {
            return ResponseEntity.badRequest().body("Error !!");
        }
        Map result = cloudinaryService.upload(multipartFile);
        Avatar avatar = new Avatar();
        avatar.setImageLink((String) result.get("url"));
        avatar.setName((String) result.get("original_filename"));
        avatar.setId((String) result.get("public_id"));
        return ResponseEntity.ok().body(avatarService.addAvatar(avatar));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) throws IOException {
        if (!avatarService.check(id)) {
            return ResponseEntity.badRequest().body("ImageProduct not found !!");
        }
        Avatar avatar = avatarService.getById(id);
        Map result = cloudinaryService.delete(avatar.getId());
        avatarService.remove(id);
        return ResponseEntity.ok().body(result);
    }
}
