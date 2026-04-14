package com.smartpark.backendapi.controller;

import com.smartpark.backendapi.dto.LoginRequest;
import com.smartpark.backendapi.dto.LoginResponse;
import com.smartpark.backendapi.model.User;
import com.smartpark.backendapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller responsible for handling authentication-related endpoints.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // Injects the AuthService dependency
    @Autowired
    private AuthService authService;

    /**
     * Endpoint for user login.
     * Accepts login credentials and returns user role if successful.
     *
     * @param request LoginRequest object containing username and password
     * @return LoginResponse containing username and role
     */
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        // Call service to validate credentials
        User user = authService.login(
                request.getUsername(),
                request.getPassword()
        );

        // Return only necessary data (not password)
        return new LoginResponse(user.getUsername(), user.getRole());
    }
}