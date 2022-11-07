package com.ark.assignment.service;

import com.ark.assignment.models.Fund;
import com.ark.assignment.models.Investor;
import com.ark.assignment.models.NewFundRequest;
import com.ark.assignment.models.NewInvestorRequest;

public interface InvestorService {

    /**
     * Creates a new Investor for a client
     *
     * @param clientId
     * @param newInvestorRequest
     * @return
     */
    Investor createInvestor(Long clientId, NewInvestorRequest newInvestorRequest);

}
