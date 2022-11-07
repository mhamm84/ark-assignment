package com.ark.assignment.transactions;

import com.ark.assignment.models.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InterestIncomeCalculatorTest {

    private InterestIncomeCalculator underTest;

    @BeforeEach
    public void setup() {
        underTest = new InterestIncomeCalculator();
    }

    @Test
    void calculate() {
        BigDecimal starting = BigDecimal.ZERO;
        BigDecimal amount = BigDecimal.valueOf(2000);

        BigDecimal result = underTest.calculate(starting, amount);
        assertEquals(result, BigDecimal.valueOf(2000));
    }

    @Test
    void transactionType() {
        assertEquals(underTest.transactionType(), TransactionType.INTEREST_INCOME);
    }
}