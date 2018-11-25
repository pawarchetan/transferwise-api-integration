package com.wevat.exception;

public class InvalidResponseException extends RuntimeException {
    private String message;

    public InvalidResponseException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
