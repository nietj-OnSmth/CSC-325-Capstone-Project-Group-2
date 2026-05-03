package com.smartpark.javafxclient.dto;

//This class represents the parking lot data that comes from the backend.
public class ParkingLotDTO {

    private Long id;
    private String name;
    private String allowedRole;
    private int capacity;
    private int availableSpaces;
    private double distance;
    private String status;

    public ParkingLotDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAllowedRole() {
        return allowedRole;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableSpaces() {
        return availableSpaces;
    }

    public double getDistance() {
        return distance;
    }

    public String getStatus() {
        return status;
    }
}
