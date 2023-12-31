package com.example.demo.service.impl;

import com.example.demo.domain.*;
import com.example.demo.repository.*;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PrimaryAccountRepository primaryAccountRepository;
    @Autowired
    private SavingsAccountRepository savingsAccountRepository;
    @Autowired
    private SavingsTransactionRepository savingsTransactionRepository;
    @Autowired
    private PrimaryTransactionRepository primaryTransactionRepository;
    @Autowired
    private RecipientRepository recipientRepository;

    @Override
    public List<PrimaryTransaction> findPrimaryTransactionList(String username) {
        User user = userRepository.findByUsername(username);
        List<PrimaryTransaction> primaryTransactions = user.getPrimaryAccount().getPrimaryTransactionList();

        return primaryTransactions;
    }

    @Override
    public List<SavingsTransaction> findSavingsTransactionList(String username) {
        User user = userRepository.findByUsername(username);
        List<SavingsTransaction> savingsTransactions = user.getSavingsAccount().getSavingsTransactionList();

        return savingsTransactions;
    }

    @Override
    public void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction) {

    }

    @Override
    public void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction) {

    }

    @Override
    public void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction) {

    }

    @Override
    public void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction) {

    }

    @Override
    public void betweenAccountsTransfer(String from, String to, String amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount) {
        if (from.equalsIgnoreCase("Primary") && to.equalsIgnoreCase("Savings")){
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));

            primaryAccountRepository.save(primaryAccount);
            savingsAccountRepository.save(savingsAccount);
            Date date = new Date();
            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date,"between account transfer "+from+" to "+to,"Account","Finished",Double.parseDouble(amount),primaryAccount.getAccountBalance(),primaryAccount);

            primaryTransactionRepository.save(primaryTransaction);

        }else if(from.equalsIgnoreCase("Savings") && to.equalsIgnoreCase("Primary")){
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));

            primaryAccountRepository.save(primaryAccount);
            savingsAccountRepository.save(savingsAccount);

            Date date = new Date();
            SavingsTransaction savingsTransaction = new SavingsTransaction(date,"between account transfer from "+from+ " account to "+to,"Account","Finished",Double.parseDouble(amount),savingsAccount.getAccountBalance(),savingsAccount);

            savingsTransactionRepository.save(savingsTransaction);
        }
    }

    @Override
    public List<Recipient> findRecipientList(Principal principal) {
        String username = principal.getName();

        List<Recipient> recipientList = recipientRepository.findAll()
                .stream()
                .filter(recipient -> username.equals(recipient.getUser().getUsername()))
                .collect(Collectors.toList());

        return recipientList;
    }

    @Override
    public Recipient saveRecipient(Recipient recipient) {
        return recipientRepository.save(recipient);
    }

    @Override
    public Recipient deleteRecipientByName(String name) {
        return recipientRepository.deleteByName(name);
    }

    @Override
    public Recipient findRecipientByName(String name) {
        return recipientRepository.findByName(name);
    }

    @Override
    public void toSomeoneElseTransfer(Recipient recipient, String accountType, String amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount) {
        if (accountType.equalsIgnoreCase("Primary")){
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            primaryAccountRepository.save(primaryAccount);

            Date date = new Date();
            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date,"Transfer to recipient"+recipient.getName(),"Transfer","Finished",Double.parseDouble(amount),primaryAccount.getAccountBalance(),primaryAccount);

            primaryTransactionRepository.save(primaryTransaction);

        }else if (accountType.equalsIgnoreCase("Savings")){
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingsAccountRepository.save(savingsAccount);
            Date date = new Date();
            SavingsTransaction savingsTransaction = new SavingsTransaction(date,"Transfer to recipient"+recipient.getName(),"Transfer","Finished",Double.parseDouble(amount),savingsAccount.getAccountBalance(),savingsAccount);

            savingsTransactionRepository.save(savingsTransaction);
        }
    }
}

















