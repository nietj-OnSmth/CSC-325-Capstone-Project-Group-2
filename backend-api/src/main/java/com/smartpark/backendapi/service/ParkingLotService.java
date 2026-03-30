package com.smartpark.backendapi.service;

import com.smartpark.backendapi.model.ParkingLot;
import com.smartpark.backendapi.model.UserRole;
import com.smartpark.backendapi.repository.ParkingLotRepository;
import org.springframework.stereotype.Service;
import com.smartpark.backendapi.exception.LotNotFoundException;
import com.smartpark.backendapi.exception.NoAvailableLotException;

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
                .orElseThrow(() -> new LotNotFoundException(id));

        lot.setAvailableSpaces(spaces);

        // Update lot status based on remaining spaces
        lot.setStatus(spaces > 0 ? "AVAILABLE" : "FULL");

        return repository.save(lot);
    }

    /**
     * Finds the next best available parking lot for a given user role,
     * excluding a specific lot (usually the one that is full).
     * @param role          The user's role (STUDENT or FACULTY)
     * @param excludedLotId The ID of the lot to exclude (typically the full lot)
     * @return The next best available ParkingLot
     * @throws NoAvailableLotException if no valid lot is found
     */
    public ParkingLot getNextBestLot(UserRole role, Long excludedLotId) {

        return repository.findAll().stream()

                // Step 1: Only include lots that match the user's role
                .filter(lot -> lot.getAllowedRole() == role)

                // Step 2: Only include lots that still have available spaces
                // Prevents recommending full parking lots
                .filter(lot -> lot.getAvailableSpaces() > 0)

                // Step 3: Exclude the lot that is already full or being avoided
                // This ensures the same lot is not recommended again
                .filter(lot -> !lot.getId().equals(excludedLotId))

                // Step 4: Select the "best" lot based on shortest distance
                .min(Comparator.comparingDouble(ParkingLot::getDistance))

                // Step 5: If no valid lot is found, throw a custom exception
                // This will be handled by the GlobalExceptionHandler
                .orElseThrow(() ->
                        new NoAvailableLotException(
                                "No alternate parking lot available for role: " + role
                        ));
    }
}