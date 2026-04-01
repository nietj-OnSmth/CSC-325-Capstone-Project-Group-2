package com.smartpark.backendapi.exception;

/**
 * Custom exception thrown when a user provides
 * invalid login credentials.
 */
public class InvalidCredentialsException extends RuntimeException {

    /**
     * Constructor that accepts a custom error message.
     */
    public InvalidCredentialsException(String message) {
        super(message);
    }
}