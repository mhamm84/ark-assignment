package com.ark.assignment.config;

import com.ark.assignment.models.TransactionType;
import com.ark.assignment.transactions.BalanceCalculator;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class BalanceCalculatorConfig {
    private final List<BalanceCalculator> balanceCalculators;

    @Bean
    public Map<TransactionType, BalanceCalculator> balanceCalculatorByType() {
        Map<TransactionType, BalanceCalculator> map = new EnumMap<>(TransactionType.class);
        balanceCalculators.forEach(balanceCalculator -> map.put(balanceCalculator.transactionType(), balanceCalculator));
        return map;
    }
}