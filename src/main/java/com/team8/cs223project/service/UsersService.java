package com.team8.cs223project.service;

import com.team8.cs223project.entity.Users;

public interface UsersService {
    void queryAll();

    void insertUser(Integer id, String username, String pwd);
}
