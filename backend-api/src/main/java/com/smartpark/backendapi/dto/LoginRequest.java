package com.smartpark.backendapi.dto;

/**
 * Data Transfer Object (DTO) used to receive login requests
 * from the frontend. Contains user-entered credentials.
 */
public class LoginRequest {

    // Username entered by the user
    private String username;

    // Password entered by the user
    private String password;


    // Default constructor
    public LoginRequest() {}


    // Parameterized constructor
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter and Setter methods

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}