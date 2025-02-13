package patientsServs.patientServicesEnrolment.serviceLayer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import patientsServs.patientServicesEnrolment.DTO.LocationDTO;
import patientsServs.patientServicesEnrolment.models.Location;
import patientsServs.patientServicesEnrolment.repositories.LocationRepository;
import patientsServs.patientServicesEnrolment.serviceIMP.LocationServiceIMP;

@Service
public class LocationServices implements LocationServiceIMP {

    // Logger instance for logging service activities
    private static final Logger logger = LoggerFactory.getLogger(LocationServices.class);

    @Autowired
    private LocationRepository locationRep;

    /**
     * Saves a new location in the database.
     * @param location - Location entity to be added.
     * @return The saved Location entity.
     */
    @Override
    public Location Add_Location(Location location) {
        logger.info("Adding new location: {}", location.getCountry());
        Location savedLocation = locationRep.save(location);
        logger.info("Location added successfully with ID: {}", savedLocation.getId());
        return savedLocation;
    }

    /**
     * Retrieves all locations without associated hospitals.
     * @return List of LocationDTO objects.
     */
    @Override
    public List<LocationDTO> locations() {
        logger.info("Fetching all locations without hospitals.");
        List<LocationDTO> locationList = locationRep.findAllLocationsWithoutHospitals();
        logger.info("Total locations retrieved: {}", locationList.size());
        return locationList;
    }
}
