package com.smartpark.backendapi.service;

import com.smartpark.backendapi.model.ParkingLot;
import com.smartpark.backendapi.model.UserRole;
import com.smartpark.backendapi.repository.ParkingLotRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * Service layer responsible for business logic related to parking lots.
 * This class sits between the controller and repository layers.
 */
@Service
public class ParkingLotService {

    private final ParkingLotRepository repository;
    private final RecommendationStrategy recommendationStrategy;

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

        // Update lot status based on remaining spaces
        lot.setStatus(spaces > 0 ? "AVAILABLE" : "FULL");

        return repository.save(lot);
    }

    /**
     * Returns the next best parking lot after excluding a specific lot.
     * This is used for rerouting when the user's selected lot becomes full
     * or unavailable.
     * @param role the user's role (STUDENT or FACULTY)
     * @param excludedLotId the lot that should not be considered
     * @return the next best available lot, or null if none exists
     */
    public ParkingLot getNextBestLot(UserRole role, Long excludedLotId) {
        return repository.findAll().stream()

                // Only include lots valid for the selected role
                .filter(lot -> lot.getAllowedRole() == role)

                // Only include lots with open spaces
                .filter(lot -> lot.getAvailableSpaces() > 0)

                // Exclude the lot that became full / unavailable
                .filter(lot -> !lot.getId().equals(excludedLotId))

                // Return the nearest remaining valid lot
                .min(Comparator.comparingDouble(ParkingLot::getDistance))
                .orElse(null);
    }
}