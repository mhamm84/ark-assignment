package com.ark.assignment.transactions;

import com.ark.assignment.models.Transaction;
import com.ark.assignment.models.TransactionType;

import java.math.BigDecimal;

public interface BalanceCalculator {

    BigDecimal calculate(BigDecimal startingBalance, TransactionType txnType, BigDecimal amount);

    TransactionType transactionType();
}
