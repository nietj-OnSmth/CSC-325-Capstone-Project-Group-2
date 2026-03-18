package com.smartpark.backendapi.service;

import com.smartpark.backendapi.model.ParkingLot;
import com.smartpark.backendapi.model.UserRole;
import com.smartpark.backendapi.repository.ParkingLotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer responsible for business logic related to parking lots.
 *
 * This class sits between the controller and repository layers.
 *
 * Responsibilities:
 * - retrieving parking lots
 * - filtering lots by role
 * - generating recommendations
 * - updating availability
 *
 * Demonstrates the Single Responsibility Principle.
 */
@Service
public class ParkingLotService {

    private final ParkingLotRepository repository;
    private final RecommendationStrategy recommendationStrategy;

    /**
     * Constructor injection for dependencies.
     */
    public ParkingLotService(ParkingLotRepository repository,
                             RecommendationStrategy recommendationStrategy) {
        this.repository = repository;
        this.recommendationStrategy = recommendationStrategy;
    }

    /**
     * Returns all parking lots in the system.
     */
    public List<ParkingLot> getAllLots() {
        return repository.findAll();
    }

    /**
     * Returns parking lots available for a specific role.
     */
    public List<ParkingLot> getAvailableLotsForRole(UserRole role) {
        return repository.findByAllowedRole(role).stream()
                .filter(lot -> lot.getAvailableSpaces() > 0)
                .toList();
    }

    /**
     * Returns the recommended parking lot based on the
     * selected recommendation strategy.
     */
    public ParkingLot getRecommendedLot(UserRole role) {
        return recommendationStrategy.recommend(repository.findAll(), role);
    }

    /**
     * Updates the number of available spaces in a parking lot.
     * Used for admin simulation or sensor updates.
     */
    public ParkingLot updateAvailability(Long id, int spaces) {

        ParkingLot lot = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lot not found"));

        lot.setAvailableSpaces(spaces);

        // Update status based on availability
        lot.setStatus(spaces > 0 ? "AVAILABLE" : "FULL");

        return repository.save(lot);
    }
}