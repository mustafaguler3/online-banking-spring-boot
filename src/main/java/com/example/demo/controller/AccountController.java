package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @GetMapping("/primaryAccount")
    public String primaryAccount(){
        return "primaryAccount";
    }

    @GetMapping("/savingsAccount")
    public String savingsAccount(){
        return "savingsAccount";
    }

}

















