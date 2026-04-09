package com.smartpark.backendapi.controller;

import com.smartpark.backendapi.exception.AccessDeniedException;
import com.smartpark.backendapi.model.ParkingLot;
import com.smartpark.backendapi.model.UserRole;
import com.smartpark.backendapi.service.ParkingLotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST controller that exposes parking-related API endpoints.
 */
@RestController
@RequestMapping("/api/lots")
@CrossOrigin
public class ParkingLotController {

    private final ParkingLotService service;

    public ParkingLotController(ParkingLotService service) {
        this.service = service;
    }

    /**
     * Returns all parking lots.
     */
    @GetMapping
    public List<ParkingLot> getAllLots() {
        return service.getAllLots();
    }

    /**
     * Returns a single parking lot by its ID.
     * Example:
     * /api/lots/1
     */
    @GetMapping("/{id}")
    public ParkingLot getLotById(@PathVariable Long id) {
        return service.getLotById(id);
    }

    /**
     * Ensures that only admins can access certain endpoints.
     *
     * @param role the role supplied in the request
     * @throws AccessDeniedException if the role is not ADMIN
     */
    private void requireAdmin(UserRole role) {
        if (role != UserRole.ADMIN) {
            throw new AccessDeniedException("Access denied: Admins only");
        }
    }

    /**
     * Creates a new parking lot.
     * Admin only.
     * Example:
     * POST /api/lots?role=ADMIN
     */
    @PostMapping
    public ParkingLot createLot(@RequestBody ParkingLot lot,
                                @RequestParam UserRole role) {
        requireAdmin(role);
        return service.createLot(lot);
    }

    /**
     * Updates an existing parking lot's details.
     * Admin only.
     * Example:
     * PUT /api/lots/1?role=ADMIN
     */

    @PutMapping("/{id}")
    public ParkingLot updateLot(@PathVariable Long id,
                                @RequestBody ParkingLot updatedLot,
                                @RequestParam UserRole role) {
        requireAdmin(role);
        return service.updateLot(id, updatedLot);
    }

    /**
     * Deletes a parking lot from the system.
     * Admin only.
     * Example:
     * DELETE /api/lots/1?role=ADMIN
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteLot(@PathVariable Long id,
                                                         @RequestParam UserRole role) {
        requireAdmin(role);
        service.deleteLot(id);
        return ResponseEntity.ok(Map.of("message", "Lot deleted successfully"));
    }

    /**
     * Returns all available lots for a given user role.
     * Example:
     * /api/lots/available?role=STUDENT
     */
    @GetMapping("/available")
    public List<ParkingLot> getAvailableLots(@RequestParam UserRole role) {
        return service.getAvailableLotsForRole(role);
    }

    /**
     * Returns the recommended lot for the given user role.
     * Example:
     * /api/lots/recommended?role=STUDENT
     */
    @GetMapping("/recommended")
    public ParkingLot getRecommendedLot(@RequestParam UserRole role) {
        return service.getRecommendedLot(role);
    }

    /**
     * Updates the number of available spaces in a parking lot.
     * Admin only.
     * Used for admin simulation.
     * Example:
     * /api/lots/1/availability?spaces=0&role=ADMIN
     */
    @PutMapping("/{id}/availability")
    public ParkingLot updateAvailability(@PathVariable Long id,
                                         @RequestParam int spaces,
                                         @RequestParam UserRole role) {
        requireAdmin(role);
        return service.updateAvailability(id, spaces);
    }

    /**
     * Returns the next best lot when the currently selected lot becomes full.
     * Example:
     * /api/lots/reroute?role=STUDENT&excludedLotId=1
     */
    @GetMapping("/reroute")
    public ParkingLot rerouteLot(@RequestParam UserRole role,
                                 @RequestParam Long excludedLotId) {
        return service.getNextBestLot(role, excludedLotId);
    }
}