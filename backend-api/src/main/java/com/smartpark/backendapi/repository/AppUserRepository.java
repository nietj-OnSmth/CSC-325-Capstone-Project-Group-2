package com.smartpark.backendapi.repository;

import com.smartpark.backendapi.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for accessing AppUser data from the database.
 * Provides built-in CRUD operations through JpaRepository.
 */
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    /**
     * Finds a user by their username.
     * Used for login authentication.
     */
    Optional<AppUser> findByUsername(String username);

    /**
     * Checks if a username already exists.
     * Useful if time permits for user registration to be added
     */
    boolean existsByUsername(String username);
}