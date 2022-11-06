package com.ark.assignment.service;

import com.ark.assignment.entity.*;
import com.ark.assignment.exception.ErrorCode;
import com.ark.assignment.exception.FundNotFoundException;
import com.ark.assignment.exception.TransactionTypeNotFoundException;
import com.ark.assignment.models.*;
import com.ark.assignment.repository.*;
import com.ark.assignment.transactions.BalanceCalculatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final ClientRepository clientRepository;
    private final FundRepository fundRepository;
    private final InvestorRepository investorRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionTypeRepository transactionTypeRepository;

    private final BalanceCalculatorContext balanceCalculatorContext;

    @Transactional
    @Override
    public Transaction applyTransaction(Long clientId, Long fundId, Long investorId, TransactionRequest transactionRequest) {

        TransactionTypeEntity txnType = transactionTypeRepository.findByName(transactionRequest.getTxnType().getValue());
        if(txnType == null) {
            throw new TransactionTypeNotFoundException(
                    ErrorCode.TRANSACTION_TYPE_NOT_FOUNT,
                    String.format("transaction type with value %s, not found", transactionRequest.getTxnType().getValue())
            );
        }
        InvestorEntity investorEntity = investorRepository.getReferenceById(investorId);
        FundEntity fundEntity = fundRepository.findById(fundId)
                .orElseThrow(() -> new FundNotFoundException(String.format("could not find fund with id: %d", fundId)));

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(BigDecimal.valueOf(transactionRequest.getAmount()));
        transactionEntity.setType(txnType);
        transactionEntity.setInvestor(investorEntity);
        transactionEntity.setFund(fundEntity);
        transactionEntity = transactionRepository.save(transactionEntity);

        fundEntity.setBalance(
                balanceCalculatorContext.calculateBalance(
                        fundEntity.getBalance(),
                        transactionRequest.getTxnType(),
                        BigDecimal.valueOf(transactionRequest.getAmount())
                )
        );
        fundEntity = fundRepository.save(fundEntity);

        Transaction transactionModel = new Transaction();
        transactionModel.setId(transactionEntity.getId());
        transactionModel.setAmount(transactionEntity.getAmount().doubleValue());
        transactionModel.setTxnType(TransactionType.fromValue(transactionEntity.getType().getName()));
        transactionModel.setCreated(transactionEntity.getCreatedDateTime());

        log.info(String.format("transaction: %s, applied to fund: %s", transactionModel, fundEntity));

        return transactionModel;
    }

    @Transactional
    @Override
    public FundSummaryResponse findTransactionsForInvestor(Long clientId, Long fundId, Long investorId, LocalDate start, LocalDate end, TransactionType type) {

        List<TransactionEntity> txns;
        List<TransactionTypeSummaryEntity> summary;

        if(start != null && end != null) {
            txns =  transactionRepository.findTransactionsForInvestorBetween(fundId, investorId, start.atStartOfDay(), end.atStartOfDay());
            summary = transactionRepository.findTransactionsForInvestorBetweenSummary(fundId, investorId, start.atStartOfDay(), end.atStartOfDay());
        }else if(start != null) {
            txns =  transactionRepository.findTransactionsForInvestorFrom(fundId, investorId, start.atStartOfDay());
            summary = transactionRepository.findTransactionsForInvestorFromSummary(fundId, investorId, start.atStartOfDay());
        } else {
            txns =  transactionRepository.findTransactionsForInvestor(fundId, investorId);
            summary = transactionRepository.findTransactionsForInvestorSummary(fundId, investorId);
        }

        List<Transaction> modelTxns;
        List<TransactionTypeSummary> modelSummaries;

        boolean filerByType = type != null ? true : false;
        if(filerByType) {
            modelTxns = txns.stream()
                    .filter(t -> t.getType().getName().equals(type.getValue()))
                    .map(t -> toModel(t))
                    .collect(Collectors.toList());
        } else {
            modelTxns = txns.stream()
                    .map(t -> toModel(t))
                    .collect(Collectors.toList());
        }

        modelSummaries = summary.stream()
                .map(s -> toModel(s))
                .collect(Collectors.toList());


        FundSummaryResponse response = new FundSummaryResponse();
        response.setTransactions(modelTxns);
        response.setSummaries(modelSummaries);

        return response;
    }

    public static Transaction toModel(TransactionEntity entity) {
        Transaction t = new Transaction();
        t.setId(entity.getId());
        t.setAmount(entity.getAmount().doubleValue());
        t.setCreated(entity.getCreatedDateTime());
        t.setTxnType(toModel(entity.getType()));

        return t;
    }

    public static TransactionType toModel(TransactionTypeEntity entityType) {
        return TransactionType.fromValue(entityType.getName());
    }

    public static TransactionTypeSummary toModel(TransactionTypeSummaryEntity entity) {
        TransactionTypeSummary model = new TransactionTypeSummary();
        model.setTxnType(TransactionType.fromValue(entity.getType()));
        model.setTotal(entity.getTotal().doubleValue());
        return model;
    }
}
