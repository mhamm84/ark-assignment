package com.ark.assignment.service;

import com.ark.assignment.models.Investor;
import com.ark.assignment.models.NewInvestorRequest;
import com.ark.assignment.models.Transaction;
import com.ark.assignment.models.TransactionRequest;

public interface TransactionService {

    Transaction applyTransaction(Long clientId, Long fundId, Long investorId, TransactionRequest transactionRequest);

}
