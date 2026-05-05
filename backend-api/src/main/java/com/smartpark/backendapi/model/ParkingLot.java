package com.smartpark.backendapi.model;

import jakarta.persistence.*;

/**
 * Represents a parking lot entity stored in the database.
 *
 * Each parking lot contains information about:
 * - the lot name
 * - who is allowed to park there
 * - capacity and available spaces
 * - distance from the destination
 * - current status (AVAILABLE / FULL)
 */
@Entity
public class ParkingLot {

    // Primary key for the parking lot table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name of the parking lot (ex: Lot A)
    private String name;

    // Role allowed to park in this lot
    @Enumerated(EnumType.STRING)
    private UserRole allowedRole;

    // Maximum number of spaces in the lot
    private int capacity;

    // Current number of available spaces
    private int availableSpaces;

    // Distance from the user's destination (used for recommendations)
    private double distance;

    // Status of the lot (AVAILABLE/LIMITED/FULL)
    private String status;

    /**
     * Default constructor required by JPA.
     */
    public ParkingLot() {}

    /**
     * Constructor used when creating parking lot objects.
     */
    public ParkingLot(String name, UserRole allowedRole, int capacity, int availableSpaces, double distance, String status) {
        this.name = name;
        this.allowedRole = allowedRole;
        this.capacity = capacity;
        this.availableSpaces = availableSpaces;
        this.distance = distance;
        this.status = status;
    }

    // Standard getters and setters

    public Long getId() { return id; }

    public String getName() { return name; }

    public UserRole getAllowedRole() { return allowedRole; }

    public int getCapacity() { return capacity; }

    public int getAvailableSpaces() { return availableSpaces; }

    public double getDistance() { return distance; }

    public String getStatus() { return status; }

    public void setId(Long id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setAllowedRole(UserRole allowedRole) { this.allowedRole = allowedRole; }

    public void setCapacity(int capacity) { this.capacity = capacity; }

    public void setAvailableSpaces(int availableSpaces) { this.availableSpaces = availableSpaces; }

    public void setDistance(double distance) { this.distance = distance; }

    public void setStatus(String status) { this.status = status; }
}