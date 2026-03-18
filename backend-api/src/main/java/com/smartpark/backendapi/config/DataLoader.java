package com.smartpark.backendapi.config;

import com.smartpark.backendapi.model.ParkingLot;
import com.smartpark.backendapi.model.UserRole;
import com.smartpark.backendapi.repository.ParkingLotRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Loads sample parking lot data when the application starts.
 *
 * This allows the backend to immediately return data
 * without needing manual database input.
 */
@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(ParkingLotRepository repo) {

        return args -> {

            // Only insert sample data if database is empty
            if (repo.count() == 0) {

                repo.save(new ParkingLot("Lot A", UserRole.STUDENT, 100, 24, 150.0, "AVAILABLE"));
                repo.save(new ParkingLot("Lot B", UserRole.STUDENT, 90, 0, 220.0, "FULL"));
                repo.save(new ParkingLot("Lot C", UserRole.FACULTY, 60, 18, 100.0, "AVAILABLE"));
                repo.save(new ParkingLot("Lot D", UserRole.FACULTY, 75, 5, 180.0, "AVAILABLE"));
            }
        };
    }
}