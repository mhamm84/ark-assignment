package com.ark.assignment.exception;

public class InvestorNotFoundException extends ArkException {

    public InvestorNotFoundException(String message, Throwable cause) {
        super(ErrorCode.INVESTOR_NOT_FOUND, message, cause);
    }

    public InvestorNotFoundException(String message) {
        super(ErrorCode.INVESTOR_NOT_FOUND, message);
    }
}
