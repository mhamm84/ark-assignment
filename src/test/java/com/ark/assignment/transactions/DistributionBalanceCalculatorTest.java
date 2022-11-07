package com.ark.assignment.transactions;

import com.ark.assignment.models.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DistributionBalanceCalculatorTest {

    private GeneralExpenseCalculator underTest;

    @BeforeEach
    public void setup() {
        underTest = new GeneralExpenseCalculator();
    }

    @Test
    void calculate() {
        BigDecimal starting = BigDecimal.valueOf(2000);
        BigDecimal amount = BigDecimal.valueOf(100);

        BigDecimal result = underTest.calculate(starting, amount);
        assertEquals(result, BigDecimal.valueOf(1900));
    }

    @Test
    void transactionType() {
        assertEquals(underTest.transactionType(), TransactionType.GENERAL_EXPENSE);
    }
}