package com.ark.assignment.service;

import com.ark.assignment.entity.FundEntity;
import com.ark.assignment.entity.InvestorEntity;
import com.ark.assignment.entity.TransactionEntity;
import com.ark.assignment.entity.TransactionTypeEntity;
import com.ark.assignment.exception.ErrorCode;
import com.ark.assignment.exception.FundNotFoundException;
import com.ark.assignment.exception.TransactionTypeNotFoundException;
import com.ark.assignment.models.Transaction;
import com.ark.assignment.models.TransactionRequest;
import com.ark.assignment.models.TransactionType;
import com.ark.assignment.repository.*;
import com.ark.assignment.transactions.BalanceCalculatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.OffsetDateTime;

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
}
