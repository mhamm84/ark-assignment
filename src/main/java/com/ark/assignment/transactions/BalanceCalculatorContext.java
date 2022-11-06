package com.ark.assignment.transactions;

import com.ark.assignment.exception.BalanceCalculatorNotFoundException;
import com.ark.assignment.models.Transaction;
import com.ark.assignment.models.TransactionType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
@Component
@Slf4j
public class BalanceCalculatorContext {

    private final Map<TransactionType, BalanceCalculator> balanceCalculators;

    public BigDecimal calculateBalance(BigDecimal startingBalance, TransactionType txnType, BigDecimal amount) throws BalanceCalculatorNotFoundException {
        BalanceCalculator balanceCalculator = balanceCalculators.getOrDefault(txnType, null);
        if (Objects.isNull(balanceCalculator)) {
            throw new BalanceCalculatorNotFoundException("Balance calculator not found for Transaction Type not found. type: " + txnType);
        }
        return balanceCalculator.calculate(startingBalance, txnType, amount);
    }
}


