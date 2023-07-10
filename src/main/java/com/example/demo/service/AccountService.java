package com.example.demo.service;

import com.example.demo.domain.PrimaryAccount;
import com.example.demo.domain.SavingsAccount;

import java.security.Principal;

public interface AccountService {
    PrimaryAccount createPrimaryAccount();
    SavingsAccount createSavingsAccount();
    void deposit(String accountType, double amount, Principal principal);
    void withdraw(String accountType,double amount,Principal principal);
}


















