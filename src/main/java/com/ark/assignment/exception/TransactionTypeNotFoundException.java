package com.ark.assignment.exception;

public class TransactionTypeNotFoundException extends ArkException {

    public TransactionTypeNotFoundException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public TransactionTypeNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
