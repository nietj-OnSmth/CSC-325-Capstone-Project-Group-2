package com.smartpark.backendapi.service;

import com.smartpark.backendapi.model.User;
import com.smartpark.backendapi.model.UserRole;
import com.smartpark.backendapi.exception.InvalidCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class responsible for handling authentication logic.
 * This acts as a bridge between the controller and the data source.
 */
@Service
public class AuthService {

    /**
     * Temporary in-memory list acting as a fake database fitting the scope of this project.
     * Stores predefined users for testing purposes.
     */
    private final List<User> users = List.of(
            new User("student1", "Studentpass", UserRole.STUDENT),
            new User("faculty1", "Facultypass", UserRole.FACULTY),
            new User("admin1", "Adminpass", UserRole.ADMIN)
    );

    /**
     * Validates user login credentials.
     *
     * @param username the username provided by the user
     * @param password the password provided by the user
     * @return the authenticated User object
     * @throws InvalidCredentialsException if credentials are incorrect
     */
    public User login(String username, String password) {

        return users.stream()
                // Check if username matches
                .filter(user -> user.getUsername().equals(username))

                // Check if password matches
                .filter(user -> user.getPassword().equals(password))

                // Return first matching user if found
                .findFirst()

                // Throw exception if no match is found
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid username or password"));
    }
}