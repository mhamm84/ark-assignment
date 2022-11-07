package com.ark.assignment.service;

import com.ark.assignment.models.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {

    /**
     * Applies a transaction to a fund
     *
     * @param clientId
     * @param fundId
     * @param investorId
     * @param transactionRequest
     * @return
     */
    Transaction applyTransaction(Long clientId, Long fundId, Long investorId, TransactionRequest transactionRequest);

    /**
     * pulls data to create a basic report for an investor based on their transactions for a func
     *
     * @param clientId
     * @param fundId
     * @param investorId
     * @param start
     * @param end
     * @param type
     * @return
     */
    FundSummaryResponse investorReport(Long clientId, Long fundId, Long investorId, LocalDate start, LocalDate end, TransactionType type);

    /**
     * pulls data to create a basic report for a fund
     *
     * @param clientId
     * @param fundId
     * @param start
     * @param end
     * @param type
     * @return
     */
    FundSummaryResponse fundReport(Long clientId, Long fundId, LocalDate start, LocalDate end, TransactionType type);


}
