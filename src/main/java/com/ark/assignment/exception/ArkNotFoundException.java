package com.ark.assignment.exception;

public class ArkNotFoundException extends ArkException {

    public ArkNotFoundException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public ArkNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
