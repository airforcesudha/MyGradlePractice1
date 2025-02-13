package patientsServs.patientServicesEnrolment.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import patientsServs.patientServicesEnrolment.DTO.AddDeptListRequest;
import patientsServs.patientServicesEnrolment.models.Hospital;
import patientsServs.patientServicesEnrolment.serviceLayer.HospitalServices;

@RestController
@RequestMapping("/api")
public class HospitalServicesController {

    // Logger to log messages at different levels (INFO, ERROR, etc.)
    private static final Logger logger = LoggerFactory.getLogger(HospitalServicesController.class);

    @Autowired
    private HospitalServices hospitalService;

    // Creating a new hospital
    @PostMapping("/hospital")
    public ResponseEntity<String> newHospital(@RequestBody Hospital hospital) {
        // Log entry with hospital details (for debugging purposes)
        logger.info("Entered newHospital method with hospital details: {}", hospital);

        try {
            // Log hospital departments for debugging
            logger.info("Hospital departments: {}", hospital.getDepartments());

            hospitalService.saveNewHospita(hospital);
            
            // Log success message
            logger.info("Successfully registered hospital with ID: {}", hospital.getId());
            
            return ResponseEntity.status(HttpStatus.OK).body("SUCCESSFULLY HOSPITAL REGISTRED.");

        } catch (DataIntegrityViolationException e) {
            // Log specific error message for DataIntegrityViolationException
            logger.error("Error: Location ID {} not found during hospital registration", hospital.getLocation().getId(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR : GIVEN LOCATION ID : " + hospital.getLocation().getId() + " WAS NOT FOUND");
        } catch (Exception e) {
            // Log generic error message and exception
            logger.error("Error occurred while registering hospital: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR : " + e.getMessage());
        }
    }

    // Adding a list of department IDs to a specific hospital
    @PostMapping("/addDepartments/{hospitalId}")
    public ResponseEntity<Object> addDepartmnet(@PathVariable("hospitalId") long hospital_id, @RequestBody AddDeptListRequest dList) {
        // Log entry with hospitalId and department list (for debugging purposes)
        logger.info("Entered addDepartmnet method for hospitalId: {} with department IDs: {}", hospital_id, dList.getDepts_ids());

        try {
            // Call the service method to add departments and log the success
            logger.info("Adding departments to hospital ID: {}", hospital_id);
            return ResponseEntity.status(HttpStatus.OK).body(hospitalService.addDept(hospital_id, dList.getDepts_ids()));
        } catch (Exception e) {
            // Log error if something goes wrong
            logger.error("Error occurred while adding departments to hospital ID: {}", hospital_id, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Get hospital details by hospital ID
    @GetMapping("/hospitalById/{hospitalId}")
    public ResponseEntity<Object> getHospitalByHospitalId(@PathVariable("hospitalId") Long hospitalId) {
        // Log the hospitalId being requested
        logger.info("Entered getHospitalByHospitalId method with hospitalId: {}", hospitalId);

        try {
            // Log success if hospital found
            logger.info("Returning details for hospital ID: {}", hospitalId);
            return ResponseEntity.status(HttpStatus.OK).body(hospitalService.getHospital(hospitalId));
        } catch (Exception e) {
            // Log error if hospital not found
            logger.error("Error occurred while fetching hospital by ID: {}", hospitalId, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Get hospital details by location ID
    @GetMapping("/hospitalByLocation/{locationId}")
    public ResponseEntity<Object> getHospitalByLocation(@PathVariable("locationId") Long locationId) {
        // Log locationId being requested
        logger.info("Entered getHospitalByLocation method with locationId: {}", locationId);

        try {
            // Log success if hospitals are found for the given locationId
            logger.info("Returning hospitals for location ID: {}", locationId);
            return ResponseEntity.status(HttpStatus.OK).body(hospitalService.getHospitalByLocationId(locationId));
        } catch (Exception e) {
            // Log error if no hospitals found for the locationId
            logger.error("Error occurred while fetching hospital by location ID: {}", locationId, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hospitalService.getHospitalByLocationId(locationId));
        }
    }

    // Getting hospital by hospital name
    @GetMapping("/hospitalByName/{hospitalName}")
    public ResponseEntity<Object> getHospitalsByName(@PathVariable("hospitalName") String hospitalName) {
        // Log the hospitalName being requested
        logger.info("Entered getHospitalsByName method with hospitalName: {}", hospitalName);

        try {
            // Log success if hospitals are found by name
            logger.info("Returning hospitals with name: {}", hospitalName);
            return ResponseEntity.status(HttpStatus.OK).body(hospitalService.getHospitalByName(hospitalName));
        } catch (Exception e) {
            // Log error if no hospitals found by name
            logger.error("Error occurred while fetching hospital by name: {}", hospitalName, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
