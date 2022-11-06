package com.ark.assignment.exception;

public class InvestorPersistException extends ArkException {

    public InvestorPersistException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public InvestorPersistException(String message, ErrorCode errorCode) {
        super(errorCode, message);
    }
}
