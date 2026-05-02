package com.smartpark.backendapi.repository;

import com.smartpark.backendapi.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing Reservation entities.
 *
 * Provides built-in CRUD operations via JpaRepository,
 * along with custom query methods for reservation logic.
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    //  Checks if a user already has an active reservation.
    boolean existsByUsername(String username);

    // Retrieves a reservation by username.
    Optional<Reservation> findByUsername(String username);
}
