package com.team8.cs223project.controller;

import com.team8.cs223project.entity.Users;
import com.team8.cs223project.mapper.UserMapper;
import com.team8.cs223project.service.DataItemService;
import com.team8.cs223project.service.UsersService;
import com.team8.cs223project.utils.Result;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

@RestController
@CrossOrigin
@RequestMapping("/api/transaction")
public class TransactionController {

    @Resource
    private UsersService usersService;

    @Resource
    private DataItemService dataItemService;

    @GetMapping("read")
    public Result readItem(String message) {
        return Result.ok();
    }

    @GetMapping("commit")
    public Result commit(String message) {
        return Result.ok();
    }

    //测试Spring事务
    @Transactional
    @GetMapping("testTransactionThrowExcep")
    public void testTransactionThrowExcep(){
        usersService.insertUser(6, "xiaofang", "1234567");
//
        throw new RuntimeException("故意抛出");

    }

    @Transactional
    @GetMapping("testTransactionNoExcep")
    public Result testTransactionNoExcep(){
        usersService.insertUser(6, "xiaofang", "1234567");
        return Result.ok();
    }

    @GetMapping("transactionalMethod")
    public Result transactionalMethod() {
        final CountDownLatch latch = new CountDownLatch(1000);
        try {
            for(int i=0; i<latch.getCount(); i++){
                new Thread(() -> {
                    dataItemService.transactionWithSychronized();
                    latch.countDown();
                }).start();
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            latch.countDown();
        }
        return Result.ok();
    }

}
