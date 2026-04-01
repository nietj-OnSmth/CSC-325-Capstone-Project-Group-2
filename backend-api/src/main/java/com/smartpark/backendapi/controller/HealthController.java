package com.smartpark.backendapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple controller used to verify that the backend server
 * is running correctly.
 */
@RestController
public class HealthController {

    /**
     * Health check endpoint
     * Example:
     * http://localhost:8080/api/health
     */
    @GetMapping("/api/health")
    public String health() {
        return "OK";
    }
}