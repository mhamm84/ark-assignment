package com.ark.assignment.service;

import com.ark.assignment.entity.ClientEntity;
import com.ark.assignment.entity.FundEntity;
import com.ark.assignment.entity.InvestorEntity;
import com.ark.assignment.exception.ErrorCode;
import com.ark.assignment.exception.FundNotFoundException;
import com.ark.assignment.exception.InvestorNotFoundException;
import com.ark.assignment.models.Fund;
import com.ark.assignment.models.NewFundRequest;
import com.ark.assignment.repository.ClientRepository;
import com.ark.assignment.repository.FundRepository;
import com.ark.assignment.repository.InvestorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class FundServiceImpl implements FundService {

    private final FundRepository fundRepository;
    private final InvestorRepository investorRepository;
    private final ClientRepository clientRepository;

    @Override
    public Fund create(Long clientId, NewFundRequest newFundRequest) {

        ClientEntity clientEntity = clientRepository.getReferenceById(clientId);

        FundEntity fundEntity = new FundEntity();
        fundEntity.setName(newFundRequest.getName());
        fundEntity.setTicker(newFundRequest.getTicker());
        fundEntity.setDescription(newFundRequest.getDescription());
        fundEntity.setBalance(BigDecimal.valueOf(newFundRequest.getBalance()));
        fundEntity.setClient(clientEntity);

        fundEntity = fundRepository.save(fundEntity);

        Fund fundModel = new Fund();
        fundModel.setId(fundEntity.getId());
        fundModel.setName(fundEntity.getName());
        fundModel.setDescription(fundEntity.getDescription());
        fundModel.setTicker(fundEntity.getTicker());
        fundModel.setBalance(fundEntity.getBalance().doubleValue());

        return fundModel;
    }

    @Transactional
    @Override
    public void addInvestorToFund(Long clientId, Long fundId, Long investorId) {

        FundEntity fundEntity = fundRepository.findById(fundId)
                .orElseThrow(() -> new FundNotFoundException(String.format("Fund not found with id: %d", fundId)));

        InvestorEntity investorEntity = investorRepository.findById(investorId)
                .orElseThrow(() -> new InvestorNotFoundException(String.format("Investor not found with id: %d", investorId)));

        fundEntity.addInvestor(investorEntity);
        fundEntity = fundRepository.save(fundEntity);

        log.info(fundEntity.toString());


    }
}
