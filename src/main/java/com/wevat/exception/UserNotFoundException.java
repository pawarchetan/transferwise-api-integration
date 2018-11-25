package com.wevat.exception;

public class UserNotFoundException extends IllegalArgumentException {
    private String message;

    public UserNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
