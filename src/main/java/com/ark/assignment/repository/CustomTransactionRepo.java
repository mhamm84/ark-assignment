package com.ark.assignment.repository;

import com.ark.assignment.entity.TransactionEntity;

import java.util.List;

public interface CustomTransactionRepo {

    List<TransactionEntity> findTransactionsForInvestor(Long investorId);
}
