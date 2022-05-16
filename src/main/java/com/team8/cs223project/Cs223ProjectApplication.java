package com.team8.cs223project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.team8.cs223project.mapper")
public class Cs223ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Cs223ProjectApplication.class, args);
    }

}
