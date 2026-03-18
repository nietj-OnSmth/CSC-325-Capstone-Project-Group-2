package com.smartpark.backendapi.service;

import com.smartpark.backendapi.model.ParkingLot;
import com.smartpark.backendapi.model.UserRole;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

/**
 * Concrete implementation of RecommendationStrategy.
 *
 * This algorithm recommends the nearest available parking lot
 * that the user is allowed to park in.
 */
@Component
public class NearestLotStrategy implements RecommendationStrategy {

    @Override
    public ParkingLot recommend(List<ParkingLot> lots, UserRole role) {

        return lots.stream()

                // Only include lots allowed for the user role
                .filter(lot -> lot.getAllowedRole() == role)

                // Only include lots with available spaces
                .filter(lot -> lot.getAvailableSpaces() > 0)

                // Select the lot with the smallest distance
                .min(Comparator.comparingDouble(ParkingLot::getDistance))

                // Return null if no suitable lot is found
                .orElse(null);
    }
}