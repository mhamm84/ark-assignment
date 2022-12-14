package com.ark.assignment.service;

import com.ark.assignment.models.*;

public interface FundService {

    /**
     * Create a new Fund for a Client
     *
     * @param clientId
     * @param newFundRequest
     * @return
     */
    Fund create(Long clientId, NewFundRequest newFundRequest);

    /**
     * Adds an investor of a client to a fund
     *
     * @param clientId
     * @param fundId
     * @param investorId
     */
    void addInvestorToFund(Long clientId, Long fundId, Long investorId);

    /**
     * Find a Fund by Id
     *
     * @param fundId
     * @return
     */
    Fund findById(Long fundId);

    /**
     * Update a Funds details
     *
     * @param clientId
     * @param fundId
     * @param updateFundRequest
     * @return
     */
    Fund updateFund(Long clientId, Long fundId, UpdateFundRequest updateFundRequest);
}
