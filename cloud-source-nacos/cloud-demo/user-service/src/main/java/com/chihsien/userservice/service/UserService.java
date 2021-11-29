package com.chihsien.userservice.service;

import com.chihsien.userservice.pojo.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User queryById(Long id) {
        return User.of(1L, "柳岩", "聚乳代言人");
    }
}