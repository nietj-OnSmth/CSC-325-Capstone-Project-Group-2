package com.smartpark.backendapi.service;

import com.smartpark.backendapi.model.ParkingLot;
import com.smartpark.backendapi.model.UserRole;
import com.smartpark.backendapi.repository.ParkingLotRepository;
import org.springframework.stereotype.Service;
import com.smartpark.backendapi.exception.LotNotFoundException;
import com.smartpark.backendapi.exception.NoAvailableLotException;
import com.smartpark.backendapi.exception.AccessDeniedException;
import com.smartpark.backendapi.repository.ReservationRepository;
import com.smartpark.backendapi.model.Reservation;

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
    private final ReservationRepository reservationRepository;

    public ParkingLotService(ParkingLotRepository repository,
                             RecommendationStrategy recommendationStrategy, ReservationRepository reservationRepository) {
        this.repository = repository;
        this.recommendationStrategy = recommendationStrategy;
        this.reservationRepository = reservationRepository;
    }


     // Returns all parking lots in the system.
    public List<ParkingLot> getAllLots() {
        return repository.findAll();
    }


    //  Returns parking lots available for a specific role.
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
        lot.setStatus(determineLotStatus(spaces, lot.getCapacity()));

        return repository.save(lot);
    }

    /**
     * Returns a ranking value for lot status.
     * Lower values have higher priority.
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

                // Step 4: Prefer AVAILABLE lots over LIMITED, then choose nearest
                .min(Comparator
                        .comparingInt(this::getStatusPriority)
                        .thenComparingDouble(ParkingLot::getDistance))

                // Step 5: If no valid lot is found, throw a custom exception
                // This will be handled by the GlobalExceptionHandler
                .orElseThrow(() ->
                        new NoAvailableLotException(
                                "No alternate parking lot available for role: " + role
                        ));
    }

    /**
     * Returns a single parking lot by its ID.
     *
     * @param id the ID of the parking lot
     * @return the matching ParkingLot
     * @throws LotNotFoundException if no lot with that ID exists
     */
    public ParkingLot getLotById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new LotNotFoundException(id));
    }

    /**
     * Creates a new parking lot in the system.
     *
     * Before saving, the lot status is calculated on the backend
     * based on available spaces and capacity. This prevents the frontend
     * from saving an incorrect status value.
     *
     * This should only be called by an admin user from the controller layer.
     *
     * @param lot the parking lot to create
     * @return the saved ParkingLot with calculated status
     */
    public ParkingLot createLot(ParkingLot lot) {
        lot.setStatus(determineLotStatus(lot.getAvailableSpaces(), lot.getCapacity()));
        return repository.save(lot);
    }

    /**
     * Updates an existing parking lot's details.
     *
     * The backend recalculates the lot status after updating capacity
     * and available spaces. This keeps AVAILABLE, LIMITED, and FULL
     * accurate even if the frontend sends an outdated status value.
     *
     * @param id the ID of the lot to update
     * @param updatedLot the new lot data
     * @return the updated ParkingLot
     * @throws LotNotFoundException if the lot does not exist
     */
    public ParkingLot updateLot(Long id, ParkingLot updatedLot) {
        ParkingLot existingLot = repository.findById(id)
                .orElseThrow(() -> new LotNotFoundException(id));

        existingLot.setName(updatedLot.getName());
        existingLot.setCapacity(updatedLot.getCapacity());
        existingLot.setAvailableSpaces(updatedLot.getAvailableSpaces());
        existingLot.setAllowedRole(updatedLot.getAllowedRole());
        existingLot.setDistance(updatedLot.getDistance());
        existingLot.setStatus(determineLotStatus(
                existingLot.getAvailableSpaces(),
                existingLot.getCapacity()
        ));

        return repository.save(existingLot);
    }

    /**
     * Deletes a parking lot from the system.
     *
     * @param id the ID of the lot to delete
     * @throws LotNotFoundException if the lot does not exist
     */
    public void deleteLot(Long id) {
        ParkingLot existingLot = repository.findById(id)
                .orElseThrow(() -> new LotNotFoundException(id));

        repository.delete(existingLot);
    }

    /**
     * Reserves a parking spot in a lot for a specific user.
     *
     * Rules:
     * - the lot must exist
     * - the user's role must be allowed for the lot
     * - the lot must still have space
     * - the user may only hold one reservation at a time
     *
     * This method:
     *  * 1. Validates user access to the parking lot
     *  * 2. Ensures the lot has available space
     *  * 3. Ensures the user does not already have a reservation
     *  * 4. Updates the lot availability and status
     *  * 5. Persists the reservation in the database
     *
     * @param id the ID of the parking lot
     * @param role the user's role
     * @param username the logged-in username
     * @return the updated ParkingLot after reservation
     */
    public ParkingLot reserveSpot(Long id, UserRole role, String username) {

        // Retrieve the parking lot or throw error if not found
        ParkingLot lot = repository.findById(id)
                .orElseThrow(() -> new LotNotFoundException(id));

        // Ensure the user is allowed to reserve this lot
        if (lot.getAllowedRole() != role && role != UserRole.ADMIN) {
            throw new AccessDeniedException("You are not allowed to reserve a spot in this lot.");
        }

        // Ensure there are available spaces
        if (lot.getAvailableSpaces() <= 0) {
            throw new NoAvailableLotException("This parking lot is full.");
        }

        // Enforce one reservation per user
        if (reservationRepository.existsByUsername(username)) {
            throw new IllegalStateException("You already have a reserved parking spot.");
        }

        // Decrease available spaces
        lot.setAvailableSpaces(lot.getAvailableSpaces() - 1);

        // Update lot status based on new availability
        lot.setStatus(determineLotStatus(lot.getAvailableSpaces(), lot.getCapacity()));

        // Save updated lot to database
        repository.save(lot);

        // Create and save reservation record
        Reservation reservation = new Reservation(username, role, lot);
        reservationRepository.save(reservation);

        return lot;
    }

    /**
     * Releases a parking spot for a specific user.
     *
     * Rules:
     * - the lot must exist
     * - the user's role must be allowed for the lot
     * - the user must already hold a reservation
     * - the reservation must belong to this lot
     *
     * This method:
     *  * 1. Validates user access to the parking lot
     *  * 2. Ensures the user has an active reservation
     *  * 3. Ensures the reservation matches the selected lot
     *  * 4. Updates lot availability and status
     *  * 5. Removes the reservation from the database
     *
     * @param id the ID of the parking lot
     * @param role the user's role
     * @param username the logged-in username
     * @return the updated ParkingLot after release
     */
    public ParkingLot releaseSpot(Long id, UserRole role, String username) {

        // Retrieve the parking lot or throw error if not found
        ParkingLot lot = repository.findById(id)
                .orElseThrow(() -> new LotNotFoundException(id));

        // Ensure the user is allowed to release from this lot
        if (lot.getAllowedRole() != role && role != UserRole.ADMIN) {
            throw new AccessDeniedException("You are not allowed to release a spot in this lot.");
        }

        // Retrieve the user's reservation
        Reservation reservation = reservationRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("You do not have a reserved parking spot to release."));

        // Ensure the reservation corresponds to the selected lot
        if (!reservation.getParkingLot().getId().equals(id)) {
            throw new IllegalStateException("You can only release the parking spot you reserved.");
        }

        // Prevent exceeding capacity
        if (lot.getAvailableSpaces() >= lot.getCapacity()) {
            throw new IllegalStateException("This parking lot is already at full capacity.");
        }

        // Increase available spaces
        lot.setAvailableSpaces(lot.getAvailableSpaces() + 1);

        // Update status
        lot.setStatus(determineLotStatus(lot.getAvailableSpaces(), lot.getCapacity()));

        // Save updated lot
        repository.save(lot);

        // Delete reservation from database
        reservationRepository.delete(reservation);

        return lot;
    }

    /**
     * Determines the correct status of a parking lot based on
     * available spaces and total capacity.
     *
     * Rules:
     * - 0 available spaces = FULL
     * - 1 to 20% of capacity = LIMITED
     * - more than 20% of capacity = AVAILABLE
     *
     * @param availableSpaces current number of available spaces
     * @param capacity total capacity of the lot
     * @return the lot status as a String
     */
    private String determineLotStatus(int availableSpaces, int capacity) {
        if (availableSpaces <= 0) {
            return "FULL";
        }

        double percentageAvailable = (double) availableSpaces / capacity;

        if (percentageAvailable <= 0.20) {
            return "LIMITED";
        }

        return "AVAILABLE";
    }

}