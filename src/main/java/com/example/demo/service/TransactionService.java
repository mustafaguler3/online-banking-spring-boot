package com.example.demo.service;

import com.example.demo.domain.PrimaryAccount;
import com.example.demo.domain.PrimaryTransaction;
import com.example.demo.domain.SavingsAccount;
import com.example.demo.domain.SavingsTransaction;

import java.util.List;

public interface TransactionService {

    List<PrimaryTransaction> findPrimaryTransactionList(String username);
    List<SavingsTransaction> findSavingsTransactionList(String username);

    void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction);
    void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction);

    void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction);
    void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction);

    void betweenAccountsTransfer(String from, String to, String amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount);

}






















