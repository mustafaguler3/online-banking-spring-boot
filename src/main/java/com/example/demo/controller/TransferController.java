package com.example.demo.controller;

import com.example.demo.domain.PrimaryAccount;
import com.example.demo.domain.Recipient;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

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

    @RequestMapping("/recipient")
    public String recipient(Model model,Principal principal){
        List<Recipient> recipientList = transactionService.findRecipientList(principal);

        Recipient recipient = new Recipient();
        model.addAttribute("recipientList",recipientList);
        model.addAttribute("recipient",recipient);

        return "recipient";
    }

    @RequestMapping("/recipient")
    public String recipientPost(@ModelAttribute("recipient") Recipient recipient,
                                Principal principal){
        User user = userService.findByUsername(principal.getName());
        recipient.setUser(user);
        transactionService.saveRecipient(recipient);

        return "redirect:/transfer/recipient";
    }
    @RequestMapping("/recipient/edit")
    public String recipientEdit(@RequestParam("recipientName") String recipientName,
                                Model model,
                                Principal principal){
        Recipient recipient = transactionService.findRecipientByName(recipientName);
        List<Recipient> recipientList = transactionService.findRecipientList(principal);

        model.addAttribute("recipientList",recipientList);
        model.addAttribute("recipient",recipient);

        return "recipient";
    }

    @RequestMapping("/recipient/delete")
    @Transactional
    public String recipientDelete(@RequestParam("recipientName") String recipientName,
                                Model model,
                                Principal principal){
        transactionService.deleteRecipientByName(recipientName);
        List<Recipient> recipientList = transactionService.findRecipientList(principal);
        Recipient recipient = new Recipient();

        model.addAttribute("recipientList",recipientList);
        model.addAttribute("recipient",recipient);

        return "recipient";
    }
    @RequestMapping("/toSomeoneElse")
    public String toSomeoneElse(Model model,Principal principal){

        List<Recipient> recipientList = transactionService.findRecipientList(principal);

        model.addAttribute("recipientList",recipientList);
        model.addAttribute("accountType","");

        return "toSomeoneElse";
    }

    @RequestMapping("/toSomeoneElse")
    public String toSomeoneElsePost(@ModelAttribute("recipientName") String recipientName,
                                    @ModelAttribute("amount") String amount,
                                    @ModelAttribute("accountType") String accountType,
                                    Model model,Principal principal){

        User user = userService.findByUsername(principal.getName());
        Recipient recipient = transactionService.findRecipientByName(recipientName);

        transactionService.toSomeoneElseTransfer(recipient,accountType,amount,user.getPrimaryAccount(),user.getSavingsAccount());

        return "redirect:/userFront";
    }
}





















