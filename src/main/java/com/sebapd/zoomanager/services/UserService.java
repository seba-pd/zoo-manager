package com.sebapd.zoomanager.services;

import com.sebapd.zoomanager.model.User;

import java.util.List;

public interface UserService {

    void save(User user);

    <Optional>User findById(Long userId);

    User findByUsername(String username);

    List<User> findAll();
}
