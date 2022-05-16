package com.team8.cs223project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.team8.cs223project.mapper")
@EnableTransactionManagement
public class Cs223ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Cs223ProjectApplication.class, args);
    }

}
