package com.example.demo.service;

import com.example.demo.domain.PrimaryAccount;
import com.example.demo.domain.SavingsAccount;

public interface AccountService {
    PrimaryAccount createPrimaryAccount();
    SavingsAccount createSavingsAccount();
}
