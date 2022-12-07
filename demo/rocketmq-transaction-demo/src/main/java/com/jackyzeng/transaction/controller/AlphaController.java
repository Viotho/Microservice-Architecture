package com.jackyzeng.transaction.controller;

import com.jackyzeng.common.model.Result;
import com.jackyzeng.transaction.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @PostMapping("/transaction-demo")
    public Result<Void> transactionDemo(@RequestParam("id") Integer id) {
        alphaService.doWithTransaction(id);
        return Result.success();
    }
}
