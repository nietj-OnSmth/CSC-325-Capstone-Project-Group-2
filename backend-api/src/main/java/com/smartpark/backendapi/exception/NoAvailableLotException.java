package com.smartpark.backendapi.exception;

/**
 * Thrown when no valid parking lot is available for the requested role or reroute.
 */
public class NoAvailableLotException extends RuntimeException {

    public NoAvailableLotException(String message) {
        super(message);
    }
}