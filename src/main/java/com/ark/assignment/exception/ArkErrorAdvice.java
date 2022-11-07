package com.ark.assignment.exception;

import com.ark.assignment.models.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
/**
 * Global exception handlers
 */
public class ArkErrorAdvice {


    //
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handle(DataIntegrityViolationException exception) {
        log.error("Database integrity violation - bad request data", exception);
        return buildErrorResponse(new ArkException(exception, ErrorCode.DATABASE_INTEGRITY_ISSUE), exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<Object> handle(HttpRequestMethodNotSupportedException exception) {
        log.error("Method not allowed", exception);
        return buildErrorResponse(new ArkException(exception, ErrorCode.METHOD_NOT_ALLOWED), exception.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handle(HttpMessageConversionException exception) {
        log.error("Bad Request data", exception);
        return buildErrorResponse(new ArkException(exception, ErrorCode.BAD_REQUEST), exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArkNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handle(ArkNotFoundException exception) {
        log.error("Resource not found.", exception);
        return buildErrorResponse(exception, exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handle(Exception exception) {
        log.error("Unknown error occurred", exception);
        return buildErrorResponse(new ArkException(exception, ErrorCode.INTERNAL_ERROR), "Unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> buildErrorResponse(ArkException exception, String message, HttpStatus httpStatus) {
        ApiError errorResponse = new ApiError();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setMessage(message);
        errorResponse.setCode(exception.getErrorCode().code);
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}

