package com.ark.assignment.transactions;

import com.ark.assignment.models.TransactionType;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ContributionBalanceCalculatorTest {

    private ContributionBalanceCalculator underTest;

    @BeforeEach
    public void setup() {
        underTest = new ContributionBalanceCalculator();
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
        assertEquals(underTest.transactionType(), TransactionType.CONTRIBUTION);
    }
}