package com.smartpark.backendapi.exception;

/**
 * Thrown when a parking lot with a given ID cannot be found.
 */
public class LotNotFoundException extends RuntimeException {

    public LotNotFoundException(Long id) {
        super("Parking lot not found with id: " + id);
    }
}