package com.smartpark.backendapi.exception;

/**
 * Exception thrown when a user tries to access
 * a resource they are not authorized to use.
 */
public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException(String message) {
        super(message);
    }
}