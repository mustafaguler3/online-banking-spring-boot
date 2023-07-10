package com.example.demo.service.impl;

import com.example.demo.domain.PrimaryTransaction;
import com.example.demo.domain.SavingsTransaction;
import com.example.demo.domain.User;
import com.example.demo.repository.PrimaryTransactionRepository;
import com.example.demo.repository.SavingsTransactionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PrimaryTransactionRepository primaryTransactionRepository;
    @Autowired
    private SavingsTransactionRepository savingsTransactionRepository;

    @Override
    public List<PrimaryTransaction> findPrimaryTransactionList(String username) {
        User user = userRepository.findByUsername(username);
        List<PrimaryTransaction> primaryTransactions = user.getPrimaryAccount().getPrimaryTransactions();

        return primaryTransactions;
    }

    @Override
    public List<SavingsTransaction> findSavingsTransactionList(String username) {
        User user = userRepository.findByUsername(username);
        List<SavingsTransaction> savingsTransactions = user.getSavingsAccount().getSavingsTransactions();

        return savingsTransactions;
    }

    @Override
    public void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction) {

    }

    @Override
    public void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction) {

    }
}
