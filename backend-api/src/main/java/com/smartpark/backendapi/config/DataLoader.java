package com.smartpark.backendapi.config;

import com.smartpark.backendapi.model.AppUser;
import com.smartpark.backendapi.model.ParkingLot;
import com.smartpark.backendapi.model.UserRole;
import com.smartpark.backendapi.repository.ParkingLotRepository;
import com.smartpark.backendapi.repository.AppUserRepository;
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
    CommandLineRunner loadData(ParkingLotRepository repo,
                               AppUserRepository userRepo) {

        return args -> {

            // Only insert sample data if database is empty
            if (repo.count() == 0) {

                repo.save(new ParkingLot("Lot A", UserRole.STUDENT, 100, 24, 150.0, "AVAILABLE"));
                repo.save(new ParkingLot("Lot B", UserRole.STUDENT, 90, 0, 220.0, "FULL"));
                repo.save(new ParkingLot("Lot C", UserRole.FACULTY, 60, 18, 100.0, "AVAILABLE"));
                repo.save(new ParkingLot("Lot D", UserRole.FACULTY, 75, 5, 180.0, "AVAILABLE"));
            }

            // Load default users if table is empty
            if (userRepo.count() == 0) {
                userRepo.save(new AppUser("student1", "Studentpass", UserRole.STUDENT));
                userRepo.save(new AppUser("faculty1", "Facultypass", UserRole.FACULTY));
                userRepo.save(new AppUser("admin1", "Adminpass", UserRole.ADMIN));
            }
        };
    }
}