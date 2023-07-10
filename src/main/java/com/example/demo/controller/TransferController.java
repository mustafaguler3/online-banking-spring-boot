package com.example.demo.controller;

import com.example.demo.domain.PrimaryAccount;
import com.example.demo.domain.SavingsAccount;
import com.example.demo.domain.User;
import com.example.demo.service.TransactionService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class TransferController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;

    @RequestMapping("/betweenAccounts")
    public String betweenAccounts(Model model){
        model.addAttribute("transferFrom","");
        model.addAttribute("transferTo","");
        model.addAttribute("amount","");

        return "betweenAccounts";
    }

    @RequestMapping(value = "/betweenAccounts",method = RequestMethod.POST)
    public String betweenAccountsPost(@ModelAttribute("amount") String amount,
                                      @ModelAttribute("transferFrom") String from,
                                      @ModelAttribute("transferTo") String to,
                                      Principal principal){
        User user = userService.findByUsername(principal.getName());
        PrimaryAccount primaryAccount = user.getPrimaryAccount();
        SavingsAccount savingsAccount = user.getSavingsAccount();

        transactionService.betweenAccountsTransfer(from,to,amount,primaryAccount,savingsAccount);

        return "redirect:/userFront";
    }
}





















