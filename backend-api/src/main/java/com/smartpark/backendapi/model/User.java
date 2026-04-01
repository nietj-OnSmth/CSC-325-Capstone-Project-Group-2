package com.smartpark.backendapi.model;

/**
 * Represents a user in the SmartPark system.
 * This model stores login credentials and role information.
 */
public class User {

    // Username used for login
    private String username;

    // Password used for authentication (plain text for simplicity in this project)
    private String password;

    // Role of the user (determines permissions)
    private UserRole role;

    /**
     * Parameterized constructor to initialize a user with all fields.
     */
    public User(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Default constructor
     */
    public User() {}

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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}