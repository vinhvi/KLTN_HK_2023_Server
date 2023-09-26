package com.example.demo.service;

import com.example.demo.entity.Avatar;

public interface AvatarService {
    Avatar addAvatar(Avatar avatar);
    boolean check(String id);
    Boolean remove(String id);
    Avatar getById(String id);
}
