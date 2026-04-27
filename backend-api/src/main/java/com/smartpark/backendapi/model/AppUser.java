package com.smartpark.backendapi.model;

import jakarta.persistence.*;

/**
 * Represents a user in the SmartPark system.
 * This entity is stored in the database and replaces in-memory user storage.
 */
@Entity
@Table(name = "app_user") // Use a custom table name to avoid conflicts with reserved keywords
public class AppUser {

    // Primary key for the user
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Unique username used for login
    @Column(unique = true, nullable = false)
    private String username;

    // User password (plain text for now, can be hashed later)
    @Column(nullable = false)
    private String password;

    // Role of the user (STUDENT, FACULTY, ADMIN)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    // Default constructor required by JPA
    public AppUser() {}


    // Constructor used to create new users
    public AppUser(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}