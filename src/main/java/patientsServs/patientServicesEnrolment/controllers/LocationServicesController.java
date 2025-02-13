package patientsServs.patientServicesEnrolment.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import patientsServs.patientServicesEnrolment.models.Location;
import patientsServs.patientServicesEnrolment.serviceLayer.LocationServices;

@RestController
@RequestMapping("/api")
public class LocationServicesController {

    // Logger to log messages at different levels (INFO, ERROR, etc.)
    private static final Logger logger = LoggerFactory.getLogger(LocationServicesController.class);

    @Autowired
    private LocationServices locationServices;

    // Register a new location
    @PostMapping("/location")
    public ResponseEntity<String> newLocation(@RequestBody Location location) {
        // Log entry with location details
        logger.info("Entered newLocation method with location details: {}", location);

        if (location == null) {
            // Log invalid input case
            logger.warn("Received invalid location data: {}", location);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("INVALID DATA TO ADD LOCATION");
        } else {
            try {
                locationServices.Add_Location(location);

                // Log success when location is added
                logger.info("New location successfully added with details: {}", location);
                return ResponseEntity.status(HttpStatus.OK).body("NEW LOCATION WAS SUCCESSFULLY ADDED");
            } catch (Exception e) {
                // Log error when exception occurs
                logger.error("Error occurred while adding new location: {}", e.getMessage(), e);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR : " + e.getMessage());
            }
        }
    }

    // Fetching all locations
    @GetMapping("/locations")
    public ResponseEntity<Object> locations() {
        // Log entry for fetching all locations
        logger.info("Entered locations method to fetch all locations");

        try {
            // Log success when locations are successfully fetched
            logger.info("Returning list of locations");
            return ResponseEntity.status(HttpStatus.OK).body(locationServices.locations());
        } catch (Exception e) {
            // Log error when exception occurs
            logger.error("Error occurred while fetching locations: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
