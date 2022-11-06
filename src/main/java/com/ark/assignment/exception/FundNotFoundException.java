package com.ark.assignment.exception;

public class FundNotFoundException extends ArkException {

    public FundNotFoundException(String message) {
        super(ErrorCode.FUND_NOT_FOUND, message);
    }

}
