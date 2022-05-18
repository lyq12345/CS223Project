package com.team8.cs223project;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.team8.cs223project.entity.DataItem;
import com.team8.cs223project.entity.Users;
import com.team8.cs223project.mapper.UserMapper;
import com.team8.cs223project.service.DataItemService;
import com.team8.cs223project.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class Cs223ProjectApplicationTests {

    @Resource
    private UsersService usersService;

    @Resource
    private DataItemService dataItemService;

    @Test
    void contextLoads()  {
    }

    @Test
    public void findAll() {
        usersService.queryAll();
    }

    @Test
    public void testSelect1() {
    }

    @Test
    public void testAdd() {
        usersService.insertUser(6, "xiaofang", "123456");
    }

    @Test
    public void readByKey(){
        Integer value = dataItemService.readItemValue("x");
        System.out.println("Reading: " + value);
    }

    @Test
    public void writeOperation(){
        dataItemService.writeItemValue("z", 3);
        dataItemService.writeItemValue("y", 0);
    }

}
