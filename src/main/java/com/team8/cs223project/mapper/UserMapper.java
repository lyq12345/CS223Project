package com.team8.cs223project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team8.cs223project.entity.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<Users> {
}
