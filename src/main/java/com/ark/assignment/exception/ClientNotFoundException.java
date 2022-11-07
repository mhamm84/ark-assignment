package com.ark.assignment.exception;

public class ClientNotFoundException extends ArkNotFoundException {

    public ClientNotFoundException(String message) {
        super(ErrorCode.CLIENT_NOT_FOUND, message);
    }

}
