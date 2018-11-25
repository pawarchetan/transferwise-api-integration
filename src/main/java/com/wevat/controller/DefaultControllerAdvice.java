package com.wevat.controller;

import com.wevat.exception.InvalidResponseException;
import com.wevat.exception.QuoteNotFoundException;
import com.wevat.exception.UserNotFoundException;
import com.wevat.exception.WevatApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class DefaultControllerAdvice {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<WevatApiError> handleUserNotFoundException(UserNotFoundException e) {
        WevatApiError error = new WevatApiError();
        error.setStatusCode(NOT_FOUND.value());
        error.setMessage(e.getMessage());
        return new ResponseEntity<>(error, NOT_FOUND);
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(QuoteNotFoundException.class)
    public ResponseEntity<WevatApiError> handleQuoteNotFoundException(QuoteNotFoundException e) {
        WevatApiError error = new WevatApiError();
        error.setStatusCode(NOT_FOUND.value());
        error.setMessage(e.getMessage());
        return new ResponseEntity<>(error, NOT_FOUND);
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InvalidResponseException.class)
    public ResponseEntity<WevatApiError> handleInvalidResponseException(InvalidResponseException e) {
        WevatApiError error = new WevatApiError();
        error.setStatusCode(NOT_FOUND.value());
        error.setMessage(e.getMessage());
        return new ResponseEntity<>(error, INTERNAL_SERVER_ERROR);
    }
}
