package com.ark.assignment.transactions;

import com.ark.assignment.models.TransactionType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InterestIncomeCalculator implements BalanceCalculator {

    @Override
    public BigDecimal calculate(BigDecimal startingBalance, TransactionType txnType, BigDecimal amount) {
        return BigDecimal.ZERO.add(startingBalance).add(amount);
    }

    @Override
    public TransactionType transactionType() {
        return TransactionType.INTEREST_INCOME;
    }
}
