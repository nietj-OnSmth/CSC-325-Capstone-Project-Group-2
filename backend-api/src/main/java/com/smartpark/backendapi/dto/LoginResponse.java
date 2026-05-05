package com.smartpark.backendapi.dto;

import com.smartpark.backendapi.model.UserRole;

/**
 * Data Transfer Object (DTO) used to send login results
 * back to the frontend after successful authentication.
 */
public class LoginResponse {

    // Username of the authenticated user
    private String username;

    // Role assigned to the user
    private UserRole role;


    // Default constructor
    public LoginResponse() {}


    // Parameterized constructor
    public LoginResponse(String username, UserRole role) {
        this.username = username;
        this.role = role;
    }

    // Getter and Setter methods

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}