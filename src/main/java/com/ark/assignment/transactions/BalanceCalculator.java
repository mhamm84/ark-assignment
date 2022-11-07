package com.ark.assignment.transactions;

import com.ark.assignment.models.TransactionType;

import java.math.BigDecimal;

/**
 * Calculates a new Balance given a transaction type and starting amount
 */
public interface BalanceCalculator {

    BigDecimal calculate(BigDecimal startingBalance, BigDecimal amount);

    TransactionType transactionType();
}
