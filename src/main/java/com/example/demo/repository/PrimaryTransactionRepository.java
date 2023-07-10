package com.example.demo.repository;

import com.example.demo.domain.PrimaryTransaction;
import org.springframework.data.repository.CrudRepository;

public interface PrimaryTransactionRepository extends CrudRepository<PrimaryTransaction,Long> {
}
