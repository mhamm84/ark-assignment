package com.ark.assignment.service;

import com.ark.assignment.models.Client;
import com.ark.assignment.models.Fund;
import com.ark.assignment.models.NewClientRequest;
import com.ark.assignment.models.NewFundRequest;

public interface FundService {

    Fund create(Long clientId, NewFundRequest newFundRequest);

    void addInvestorToFund(Long clientId, Long fundId, Long investorId);
}
