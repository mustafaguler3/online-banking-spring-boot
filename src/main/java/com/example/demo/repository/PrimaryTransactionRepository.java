package com.example.demo.repository;

import com.example.demo.domain.PrimaryTransaction;
import com.example.demo.domain.SavingsTransaction;
import org.springframework.data.repository.CrudRepository;

public interface PrimaryTransactionRepository  {
    void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction);

}
