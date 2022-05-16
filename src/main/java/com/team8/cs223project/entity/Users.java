package com.team8.cs223project.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class Users {
    private Integer id;
    private String username;
    private String password;
}
