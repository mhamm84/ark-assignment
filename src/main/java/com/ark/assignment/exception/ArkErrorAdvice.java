package com.ark.assignment.exception;


import com.ark.assignment.models.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@RestControllerAdvice
@Slf4j
public class ArkErrorAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAllUncaughtException(Exception exception) {
        log.error("Unknown error occurred", exception);
        return buildErrorResponse(exception, "Unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> buildErrorResponse(Exception exception, HttpStatus httpStatus) {
        return buildErrorResponse(exception, exception.getMessage(), httpStatus);
    }

    private ResponseEntity<Object> buildErrorResponse(Exception exception, String message, HttpStatus httpStatus) {
        ApiError errorResponse = new ApiError();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setMessage(message);
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}


//    @ExceptionHandler(value = NotFoundNotificationStrategy.class)
//    public ResponseEntity<ExceptionResponseDto> notFoundNotificationStrategy(RuntimeException runtimeException) {
//        return new ResponseEntity<>(
//                ExceptionResponseDto.builder()
//                        .message(runtimeException.getMessage())
//                        .status(HttpStatus.NOT_FOUND)
//                        .build(),
//                HttpStatus.NOT_FOUND
//        );
//    }