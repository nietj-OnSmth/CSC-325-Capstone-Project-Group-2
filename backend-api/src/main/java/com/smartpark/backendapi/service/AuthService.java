package com.smartpark.backendapi.service;

import com.smartpark.backendapi.dto.LoginRequest;
import com.smartpark.backendapi.dto.LoginResponse;
import com.smartpark.backendapi.model.AppUser;
import com.smartpark.backendapi.repository.AppUserRepository;
import org.springframework.stereotype.Service;

/**
 * Handles authentication logic for the SmartPark system.
 * Replaces in-memory user validation with database-backed validation.
 */
@Service
public class AuthService {

    private final AppUserRepository userRepository;

    // Constructor injection of repository
    public AuthService(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Validates user login credentials.
     * Checks username and password against database records.
     */
    public LoginResponse login(LoginRequest request) {

        // Find user by username
        AppUser user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password."));

        // Validate password
        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid username or password.");
        }

        // Return user info and role for frontend routing
        return new LoginResponse(user.getUsername(), user.getRole());
    }
}