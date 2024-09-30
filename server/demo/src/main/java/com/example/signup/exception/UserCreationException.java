package com.example.signup.exception;

public class UserCreationException extends RuntimeException {
    public UserCreationException(String message) {
        super(message);
    }
}
