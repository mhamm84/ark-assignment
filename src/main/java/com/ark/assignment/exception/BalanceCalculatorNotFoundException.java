package com.ark.assignment.exception;

public class BalanceCalculatorNotFoundException extends ArkNotFoundException {

    public BalanceCalculatorNotFoundException(String message, Throwable cause) {
        super(ErrorCode.BALANCE_CALC_NOT_FOUNT, message, cause);
    }

    public BalanceCalculatorNotFoundException(String message) {
        super(ErrorCode.BALANCE_CALC_NOT_FOUNT, message);
    }
}
