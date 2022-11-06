package com.ark.assignment.exception;

public class ArkException extends RuntimeException {
    private final ErrorCode errorCode;

    public ArkException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ArkException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
