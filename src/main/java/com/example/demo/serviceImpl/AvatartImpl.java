package com.example.demo.serviceImpl;

import com.example.demo.entity.Avatar;
import com.example.demo.entity.ImageProduct;
import com.example.demo.repository.AvatarRepo;
import com.example.demo.service.AvatarService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AvatartImpl implements AvatarService {
    private final AvatarRepo avatarRepo;

    @Override
    public Avatar addAvatar(Avatar avatar) {
        return avatarRepo.save(avatar);
    }

    @Override
    public boolean check(String id) {
        return avatarRepo.existsById(id);
    }

    @Override
    public Boolean remove(String id) {
        try {
            Avatar avatar = avatarRepo.findAvatarById(id);
            avatarRepo.delete(avatar);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Avatar getById(String id) {
        return avatarRepo.findAvatarById(id);
    }
}
