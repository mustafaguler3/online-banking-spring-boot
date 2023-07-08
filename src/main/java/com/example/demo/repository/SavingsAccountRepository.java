package com.example.demo.repository;

import com.example.demo.domain.SavingsAccount;
import org.springframework.data.repository.CrudRepository;

public interface SavingsAccountRepository extends CrudRepository<SavingsAccount,Long> {
    SavingsAccount findByAccountNumber(int accountNumber);
}
