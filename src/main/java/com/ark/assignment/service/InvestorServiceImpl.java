package com.ark.assignment.service;

import com.ark.assignment.entity.ClientEntity;
import com.ark.assignment.entity.InvestorEntity;
import com.ark.assignment.exception.InvestorPersistException;
import com.ark.assignment.models.Investor;
import com.ark.assignment.models.NewInvestorRequest;
import com.ark.assignment.repository.ClientRepository;
import com.ark.assignment.repository.InvestorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvestorServiceImpl implements InvestorService {

    private final InvestorRepository investorRepository;
    private final ClientRepository clientRepository;

    @Transactional
    @Override
    public Investor createInvestor(Long clientId, NewInvestorRequest newInvestorRequest) {

        ClientEntity clientEntity = clientRepository.getReferenceById(clientId);

        InvestorEntity investorEntity = new InvestorEntity();
        investorEntity.setName(newInvestorRequest.getName());
        investorEntity.setClient(clientEntity);

        try {
            investorEntity = investorRepository.save(investorEntity);
        } catch(DataIntegrityViolationException ex) {
            log.error(String.format("error saving investorEntity: %s", investorEntity), ex);
            //throw new InvestorPersistException();
        }

        Investor investorModel = new Investor();
        investorModel.setId(investorEntity.getId());
        investorModel.setName(investorEntity.getName());

        return investorModel;
    }

}
