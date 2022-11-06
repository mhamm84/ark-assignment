package com.ark.assignment.service;

import com.ark.assignment.models.Fund;
import com.ark.assignment.models.Investor;
import com.ark.assignment.models.NewFundRequest;
import com.ark.assignment.models.NewInvestorRequest;

public interface InvestorService {

    Investor createInvestor(Long clientId, NewInvestorRequest newInvestorRequest);

}
