package com.team8.cs223project.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.team8.cs223project.entity.Users;
import com.team8.cs223project.mapper.UserMapper;
import com.team8.cs223project.service.UsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Resource
    private UserMapper userMapper;

    @Override
//    @DS("follower2")
    public void queryAll() {
        List<Users> users = userMapper.selectList(null);
        System.out.println(users);
    }
}
