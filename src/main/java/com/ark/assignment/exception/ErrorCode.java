package com.ark.assignment.exception;

public enum ErrorCode {

    INVESTOR_REPO_ERROR("error saving investor"),
    INVESTOR_NOT_FOUND("investor not found"),

    FUND_REPO_ERROR("error saving fund"),
    FUND_NOT_FOUND("fund not found"),
    CLIENT_NOT_FOUND("client not found"),

    TRANSACTION_TYPE_NOT_FOUNT("transaction type not found"),

    BALANCE_CALC_NOT_FOUNT("balance calculator type not found"),

    DATABASE_INTEGRITY_ISSUE("internal server error - database integrity issue"),

    INTERNAL_ERROR("internal server error"),
    BAD_REQUEST("request was invalid"),
    METHOD_NOT_ALLOWED("request sent with method which is not allowed")

    ;

    public final String code;

    private ErrorCode(String code) {
        this.code = code;
    }


}
