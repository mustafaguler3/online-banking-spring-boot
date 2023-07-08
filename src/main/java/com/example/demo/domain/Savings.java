package com.example.demo.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Savings {

    private Long id;
    private int accountNumber;
    private BigDecimal accountBalance;

    private List<SavingsTransaction> savingsTransactions;
}
