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

                // Only include lots the user is allowed to use
                .filter(lot -> lot.getAllowedRole() == role)

                // Exclude full lots completely
                .filter(lot -> lot.getAvailableSpaces() > 0)
                .filter(lot -> !"FULL".equalsIgnoreCase(lot.getStatus()))

                // Sort first by status priority, then by distance
                .min(Comparator
                        .comparingInt(this::getStatusPriority)
                        .thenComparingDouble(ParkingLot::getDistance))

                // Return null if no valid lot is found
                .orElse(null);
    }

    /**
     * Converts lot status into a ranking value.
     * Lower number = higher recommendation priority.
     *
     * AVAILABLE = 1
     * LIMITED   = 2
     * FULL      = 3
     */
    private int getStatusPriority(ParkingLot lot) {
        if ("AVAILABLE".equalsIgnoreCase(lot.getStatus())) {
            return 1;
        } else if ("LIMITED".equalsIgnoreCase(lot.getStatus())) {
            return 2;
        } else {
            return 3;
        }
    }
}