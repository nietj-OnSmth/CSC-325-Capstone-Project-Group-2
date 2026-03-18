package com.smartpark.backendapi.repository;

import com.smartpark.backendapi.model.ParkingLot;
import com.smartpark.backendapi.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface used to interact with the parking lot database.
 * Spring Data JPA automatically provides implementations for common
 * database operations such as save(), findAll(), delete(), etc.
 */
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {

    /**
     * Custom query method to find parking lots allowed for a specific role.
     *
     * Example:
     * findByAllowedRole(STUDENT)
     */
    List<ParkingLot> findByAllowedRole(UserRole allowedRole);
}