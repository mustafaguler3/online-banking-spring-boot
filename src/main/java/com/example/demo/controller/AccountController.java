package com.example.demo.controller;

import com.example.demo.domain.PrimaryAccount;
import com.example.demo.domain.SavingsAccount;
import com.example.demo.domain.User;
import com.example.demo.service.AccountService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;

    @GetMapping("/primaryAccount")
    public String primaryAccount(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        PrimaryAccount primaryAccount = user.getPrimaryAccount();

        model.addAttribute("primaryAccount",primaryAccount);

        return "primaryAccount";
    }

    @GetMapping("/savingsAccount")
    public String savingsAccount(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        SavingsAccount savingsAccount = user.getSavingsAccount();

        model.addAttribute("savingsAccount",savingsAccount);
        return "savingsAccount";
    }

    @GetMapping("/deposit")
    public String deposit(Model model,Principal principal){
        model.addAttribute("accountType","");
        model.addAttribute("amount","");

        return "deposit";
    }

    @GetMapping("/deposit")
    public String depositPost(@ModelAttribute("amount") String amount,
                              @ModelAttribute("accountType") String accountType,
                              Principal principal){

        accountService.deposit(accountType,Double.parseDouble(amount),principal);

        return "redirect:/userFront";
    }
}

















