package com.ark.assignment.exception;

public enum ErrorCode {

    INVESTOR_REPO_ERROR("error saving investor"),
    INVESTOR_NOT_FOUND("investor not found"),

    FUND_REPO_ERROR("error saving fund"),
    FUND_NOT_FOUND("fund not found"),

    TRANSACTION_TYPE_NOT_FOUNT("transaction type not found"),

    BALANCE_CALC_NOT_FOUNT("balance calculator type not found");

    ;

    public final String code;

    private ErrorCode(String code) {
        this.code = code;
    }


}
