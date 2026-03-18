package com.smartpark.backendapi.service;

import com.smartpark.backendapi.model.ParkingLot;
import com.smartpark.backendapi.model.UserRole;

import java.util.List;

/**
 * Strategy interface used for parking recommendation algorithms.
 *
 * This allows different recommendation strategies to be implemented
 * without modifying the rest of the system.
 *
 * Demonstrates:
 * - Abstraction
 * - Polymorphism
 */
public interface RecommendationStrategy {

    /**
     * Determines the best parking lot for a given user role.
     *
     * @param lots list of all parking lots
     * @param role role of the user (student/faculty)
     * @return recommended parking lot
     */
    ParkingLot recommend(List<ParkingLot> lots, UserRole role);
}