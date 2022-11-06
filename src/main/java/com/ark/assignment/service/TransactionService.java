package com.ark.assignment.service;

import com.ark.assignment.models.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {

    Transaction applyTransaction(Long clientId, Long fundId, Long investorId, TransactionRequest transactionRequest);

    FundSummaryResponse investorReport(Long clientId, Long fundId, Long investorId, LocalDate start, LocalDate end, TransactionType type);

    FundSummaryResponse fundReport(Long clientId, Long fundId, LocalDate start, LocalDate end, TransactionType type);


}
