package com.example.demo.repository;

import com.example.demo.domain.PrimaryAccount;
import org.springframework.data.repository.CrudRepository;

public interface PrimaryAccountRepository extends CrudRepository<PrimaryAccount,Long> {
    PrimaryAccount findByAccountNumber(int accountNumber);
}
