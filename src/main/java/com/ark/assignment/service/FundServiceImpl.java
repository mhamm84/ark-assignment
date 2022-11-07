package com.ark.assignment.service;

import com.ark.assignment.entity.ClientEntity;
import com.ark.assignment.entity.FundEntity;
import com.ark.assignment.entity.InvestorEntity;
import com.ark.assignment.exception.FundNotFoundException;
import com.ark.assignment.exception.InvestorNotFoundException;
import com.ark.assignment.models.Fund;
import com.ark.assignment.models.Investor;
import com.ark.assignment.models.NewFundRequest;
import com.ark.assignment.models.UpdateFundRequest;
import com.ark.assignment.repository.ClientRepository;
import com.ark.assignment.repository.FundRepository;
import com.ark.assignment.repository.InvestorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.stream.Collectors;

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

        return getFund(fundEntity);
    }

    @Override
    public Fund updateFund(Long clientId, Long fundId, UpdateFundRequest updateFundRequest) {
        FundEntity fundEntity = fundRepository.findById(fundId).orElseThrow(() -> new FundNotFoundException(String.format("no fund found with id %d", fundId)));
        fundEntity.setName(updateFundRequest.getName());
        fundEntity.setDescription(updateFundRequest.getDescription());
        fundEntity.setTicker(updateFundRequest.getTicker());
        fundEntity = fundRepository.save(fundEntity);

        return getFund(fundEntity);
    }

    @Override
    public Fund findById(Long fundId) {
        FundEntity fundEntity = fundRepository.findById(fundId).orElseThrow(() -> new FundNotFoundException(String.format("no fund found with id %d", fundId)));
        return getFund(fundEntity);
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

    private Fund getFund(FundEntity fundEntity) {
        Fund fundModel = new Fund();
        fundModel.setId(fundEntity.getId());
        fundModel.setName(fundEntity.getName());
        fundModel.setDescription(fundEntity.getDescription());
        fundModel.setTicker(fundEntity.getTicker());
        fundModel.setBalance(fundEntity.getBalance().doubleValue());

        if(fundEntity.getInvestors() != null) {
            fundModel.setInvestors(fundEntity.getInvestors().stream()
                    .map(inv -> toModel(inv))
                    .collect(Collectors.toList()));
        }

        return fundModel;
    }

    private Investor toModel(InvestorEntity entity){
        Investor model = new Investor();
        model.setId(entity.getId());
        model.setName(entity.getName());
        return model;
    }
}
