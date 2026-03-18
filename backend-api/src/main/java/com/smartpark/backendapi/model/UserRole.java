package com.smartpark.backendapi.model;

/**
 * Enum representing the type of user accessing the parking system.
 *
 * This determines which parking lots the user is allowed to view
 * and receive recommendations for.
 */
public enum UserRole {
    STUDENT,
    FACULTY
}