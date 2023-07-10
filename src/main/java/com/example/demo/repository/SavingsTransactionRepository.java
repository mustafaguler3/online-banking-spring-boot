package com.example.demo.repository;

import com.example.demo.domain.SavingsTransaction;
import org.springframework.data.repository.CrudRepository;

public interface SavingsTransactionRepository extends CrudRepository<SavingsTransaction,Long> {
    void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction);
}
