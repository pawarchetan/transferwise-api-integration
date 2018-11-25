package com.wevat.exception;

public class QuoteNotFoundException extends IllegalArgumentException {
    private String message;

    public QuoteNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
