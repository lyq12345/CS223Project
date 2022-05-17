package com.team8.cs223project.controller;

import com.team8.cs223project.utils.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/transaction")
public class TransactionController {
    @GetMapping("read")
    public Result readItem(String message) {
        return Result.ok();
    }

    @GetMapping("commit")
    public Result commit(String message) {
        return Result.ok();
    }

}
