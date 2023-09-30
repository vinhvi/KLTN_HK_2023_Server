package com.example.demo.service;

import com.example.demo.entity.Avatar;

public interface AvatarService {
    Avatar addAvatar(Avatar avatar);
    boolean check(int id);
    Boolean remove(int id);
    Avatar getById(int id);
}
