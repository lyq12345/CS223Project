package com.team8.cs223project;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.team8.cs223project.controller.TransactionController;
import com.team8.cs223project.entity.DataItem;
import com.team8.cs223project.entity.Users;
import com.team8.cs223project.mapper.UserMapper;
import com.team8.cs223project.service.DataItemService;
import com.team8.cs223project.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
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
        dataItemService.writeItemValue("z", 99);
        dataItemService.writeItemValue("y", 0);
        throw new RuntimeException();
    }

    @Test
    public void transactionOCCTest(){
        TransactionManager tc = new TransactionManager();
        tc.load("src/test/java/com/team8/cs223project/sequences.txt");
        tc.run();
    }

    @Test
    public void testObject(){
        com.team8.cs223project.utils.Test t1 = new com.team8.cs223project.utils.Test();
        com.team8.cs223project.utils.Test t2 = null;
        t2 = t1;
        t2.v++;
        System.out.println(t1.v);
        System.out.println(t2.v);

    }

    @Test void testIntersection(){
        HashSet<String> set1 = new HashSet<String>() {
            {
                add("x");
                add("y");
                add("z");
            }
        };
        HashSet<String> set2 = new HashSet<String>() {
            {
                add("a");
                add("b");
                add("x");
            }
        };

        HashSet<String> intersection = new HashSet<>();
        intersection.addAll(set1);
        intersection.retainAll(set2);
        System.out.println(intersection.isEmpty());
    }

}
