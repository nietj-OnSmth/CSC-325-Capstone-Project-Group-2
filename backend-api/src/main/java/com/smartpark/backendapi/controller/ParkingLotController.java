package com.smartpark.backendapi.controller;

import com.smartpark.backendapi.model.ParkingLot;
import com.smartpark.backendapi.model.UserRole;
import com.smartpark.backendapi.service.ParkingLotService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller that exposes parking-related API endpoints.
 *
 * This controller communicates with the service layer to
 * retrieve and update parking lot data.
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
     * Returns all available lots for a given user role.
     *
     * Example:
     * /api/lots/available?role=STUDENT
     */
    @GetMapping("/available")
    public List<ParkingLot> getAvailableLots(@RequestParam UserRole role) {
        return service.getAvailableLotsForRole(role);
    }

    /**
     * Returns the recommended lot for the given user role.
     */
    @GetMapping("/recommended")
    public ParkingLot getRecommendedLot(@RequestParam UserRole role) {
        return service.getRecommendedLot(role);
    }

    /**
     * Updates the number of available spaces in a parking lot.
     *
     * Used for admin simulation.
     */
    @PutMapping("/{id}/availability")
    public ParkingLot updateAvailability(@PathVariable Long id,
                                         @RequestParam int spaces) {
        return service.updateAvailability(id, spaces);
    }
}