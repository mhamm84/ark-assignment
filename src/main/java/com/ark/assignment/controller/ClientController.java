package com.ark.assignment.controller;

import com.ark.assignment.api.ClientApi;
import com.ark.assignment.models.*;
import com.ark.assignment.service.FundService;
import com.ark.assignment.service.InvestorService;
import com.ark.assignment.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class ClientController implements ClientApi {

    private final FundService fundService;
    private final InvestorService investorService;

    private final TransactionService transactionService;

    @Override
    public ResponseEntity<Void> createNewFund(Long clientId, NewFundRequest newFundRequest) {

        Fund fund = fundService.create(clientId, newFundRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(fund.getId()).toUri();
        return ResponseEntity.created(location)
                //.body(fund)
                .build();
    }



    @Override
    public ResponseEntity<Fund> getFund(Long clientId, Long fundId) {
        return ResponseEntity.ok().body(fundService.findById(fundId));
    }

    @Override
    public ResponseEntity<Void> addInvestorToFund(Long clientId, Long fundId, Long investorId) {
        fundService.addInvestorToFund(clientId, fundId, investorId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Investor> createNewInvestor(Long clientId, NewInvestorRequest newInvestorRequest) {
        Investor investor = investorService.createInvestor(clientId, newInvestorRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(investor.getId()).toUri();
        return ResponseEntity.created(location).body(investor);
    }

    @Override
    public ResponseEntity<Transaction> applyTransaction(Long clientId, Long fundId, Long investorId, TransactionRequest transactionRequest) {
        Transaction transaction = transactionService.applyTransaction(clientId, fundId, investorId, transactionRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(transaction.getId()).toUri();
        return ResponseEntity.created(location).body(transaction);
    }

    @Override
    public ResponseEntity<FundSummaryResponse> investorFundReport(Long clientId, Long fundId, Long investorId, LocalDate start, LocalDate end, TransactionType type) {
        FundSummaryResponse response = transactionService.investorReport(clientId, fundId, investorId, start, end, type);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<FundSummaryResponse> fundReport(Long clientId, Long fundId, LocalDate start, LocalDate end, TransactionType type) {
        FundSummaryResponse response = transactionService.fundReport(clientId, fundId, start, end, type);
        return ResponseEntity.ok().body(response);
    }


}
