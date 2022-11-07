package com.ark.assignment.transactions;

import com.ark.assignment.models.Transaction;
import com.ark.assignment.models.TransactionType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ContributionBalanceCalculator implements BalanceCalculator {

    @Override
    public BigDecimal calculate(BigDecimal startingBalance, BigDecimal amount) {
        return BigDecimal.ZERO.add(startingBalance).add(amount);
    }

    @Override
    public TransactionType transactionType() {
        return TransactionType.CONTRIBUTION;
    }
}
