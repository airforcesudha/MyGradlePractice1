package patientsServs.patientServicesEnrolment.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import patientsServs.patientServicesEnrolment.DTO.DOBRangeRequest;
import patientsServs.patientServicesEnrolment.DTO.DiseaseMedicineDTO;
import patientsServs.patientServicesEnrolment.DTO.PatientDTO;
import patientsServs.patientServicesEnrolment.models.Disease;
import patientsServs.patientServicesEnrolment.models.Patient;
import patientsServs.patientServicesEnrolment.models.Report;
import patientsServs.patientServicesEnrolment.serviceLayer.PatientsServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class PatientServicesController {

    private static final Logger logger = LoggerFactory.getLogger(PatientServicesController.class);

    @Autowired
    PatientsServices patientServices;

    // Save a new patient
    @PostMapping("/patient")
    public ResponseEntity<Object> savePatient(@RequestBody PatientDTO patientDetails) {
        logger.info("Attempting to save a new patient: {}", patientDetails); // Logging patient details before save
        try {
            Patient patient = patientServices.savePatient(patientDetails);
            logger.info("Patient saved successfully with ID: {}", patient.getId()); // Logging success
            return ResponseEntity.status(HttpStatus.OK).body("PATIENT SAVED SUCCESSFULLY WITH ID : " + patient.getId());
        } catch (Exception e) {
            logger.error("Error saving patient: {}", e.getMessage(), e); // Logging error with stack trace
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    
   

    // Add disease to patient
    @PostMapping("/addDiseaseTo/{patientId}")
    public ResponseEntity<Object> addDisease(@PathVariable("patientId") Long patientId, @RequestBody DiseaseMedicineDTO diseaseDetails) {
        try {
            Disease disease = patientServices.addDisease(patientId, diseaseDetails);
            logger.info("Disease added successfully for patient with ID: {}", patientId); // Logging success
            return ResponseEntity.status(HttpStatus.OK).body(disease);
        } catch (Exception e) {
            logger.error("Error adding disease to patient with ID: {}", patientId, e); // Logging error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    
    

    // Get all patients
    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        logger.info("Fetching all patients"); // Logging the request to fetch all patients
        return ResponseEntity.status(HttpStatus.OK).body(patientServices.getAllPatients());
    }

    
    
    // Get a patient by ID
    @GetMapping("patientByID/{id}")
    public ResponseEntity<Object> getPatientById(@PathVariable("id") Long id) {
        logger.info("Fetching patient with ID: {}", id); // Logging request to fetch patient by ID
        Patient patient = patientServices.getPatientById(id);
        if (patient == null) {
            logger.warn("Patient with ID {} not found", id); // Logging warning if patient not found
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Patient ID : " + id + " Not Found");
        } else {
            logger.info("Patient with ID {} found", id); // Logging success
            return ResponseEntity.status(HttpStatus.OK).body(patient);
        }
    }
    
    
    

    // Update patient details by ID
    @PutMapping("/patientById/{id}")
    public ResponseEntity<String> updatePatient(@PathVariable("id") Long id, @RequestBody Patient patient) {
        logger.info("Attempting to update patient with ID: {}", id); // Logging update attempt
        String status = patientServices.updatePatient(id, patient);
        if (status.equalsIgnoreCase("Patient NOT FOUND")) {
            logger.error("Patient with ID {} not found for update", id); // Logging error if patient not found
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("PATIENT NOT FOUND");
        } else {
            logger.info("Patient with ID {} updated successfully", id); // Logging success
            return ResponseEntity.status(HttpStatus.OK).body("UPDATE SUCCESSFULLY");
        }
    }
    
    
    

    // Get the overall records data (Total patients, diseases, etc.)
    @GetMapping("/totalRecordsData")
    public ResponseEntity<String> getNoOfPatients() {
        logger.info("Fetching the total number of registered patients"); // Logging the request
        return ResponseEntity.status(HttpStatus.OK).body(patientServices.RegistarationDetails());
    }
    
    
    

    // Delete patient by ID
    @DeleteMapping("/patientById/{id}/{password}")
    public ResponseEntity<String> DeletePatient(@PathVariable("id") Long id,@PathVariable("password") String password) {
        logger.info("Attempting to delete patient with ID: {}", id); // Logging delete attempt
        if (!patientServices.isPatientExist(id)) {
            logger.warn("Patient with ID {} not found for deletion", id); // Logging warning if patient doesn't exist
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("PATIENT NOT FOUND");
        } else {
            patientServices.deletePatient(id);
            logger.info("Patient with ID {} deleted successfully", id); // Logging success
            return ResponseEntity.status(HttpStatus.OK).body("PATIENT DELETED SUCCESSFULLY");
        }
    }
    
    
    

    // Fetch patients by DOB range
    @GetMapping("/byDateOfBirth")
    public ResponseEntity<Object> byDOB(@RequestBody DOBRangeRequest dobRangeRequest) {
        LocalDate startDate = dobRangeRequest.getStartDate();
        LocalDate endDate = dobRangeRequest.getEndDate();

        logger.info("Fetching patients by DOB range: {} - {}", startDate, endDate); // Logging request with date range

        // Validate the date range
        if (startDate.isBefore(endDate)) {
            List<Patient> patients = patientServices.byDOB(startDate, endDate);
            if (patients.isEmpty()) {
                logger.info("No patients found for the given DOB range"); // Logging when no patients are found
                return ResponseEntity.status(HttpStatus.OK).body("NO PATIENTS AT GIVEN DATE OF BIRTH RANGE.");
            } else {
                logger.info("Patients found in the given DOB range"); // Logging success
                return ResponseEntity.status(HttpStatus.OK).body(patients);
            }
        } else {
            logger.error("Invalid date range: {} - {}", startDate, endDate); // Logging error for invalid date range
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("INVALID DATE RANGE, GIVEN STARTING DATE MUST BE SMALLER THAN ENDING DATE");
        }
    }
    
    

    // Fetch patient details by name
    @GetMapping("/byName/{patientName}")
    public ResponseEntity<Object> getPatientByName(@PathVariable("patientName") String patientName) {
        logger.info("Fetching patient with name: {}", patientName); // Logging request to fetch patient by name
        if (patientServices.getPatientByName(patientName).size() != 0) {
            return ResponseEntity.status(HttpStatus.OK).body(patientServices.getPatientByName(patientName));
        } else {
            logger.warn("No patients found with the name: {}", patientName); // Logging warning if no patient found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PATIENTS NOT FOUND ON GIVEN NAME : " + patientName);
        }
    }
    
    
    

    // Adding report to patient
    @PostMapping("/PatientReport")
    public ResponseEntity<String> addPatientReport(@RequestBody Report report) {
        try {
            patientServices.addPatientReport(report);
            logger.info("Report added for patient with ID: {}", report.getPatient().getId()); // Logging report addition
            return ResponseEntity.status(HttpStatus.OK).body("REPORTED ADDED");
        } catch (Exception e) {
            logger.error("Error adding report for patient ID: {}", report.getPatient().getId(), e); // Logging error
            if (e.getMessage().contains("fkrqbatjk8h5940mhcfup3td70i")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("INVALID PATIENT ID : PATIENT ID " + report.getPatient().getId() + " WAS NOT FOUND");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }
    }
    
    

    // Fetching all patient reports based on patient ID
    @GetMapping("/patientReports/{patientId}")
    public ResponseEntity<Object> patientReports(@PathVariable("patientId") Long patientId) {
        logger.info("Fetching reports for patient with ID: {}", patientId); // Logging report fetch attempt
        try {
            if (patientServices.patientReports(patientId).isEmpty()) {
                logger.info("No reports found for patient with ID: {}", patientId); // Logging when no reports found
                return ResponseEntity.status(HttpStatus.OK).body("REPORTS NOT FOUND ON PATIENT ID : " + patientId);
            } else {
                logger.info("Reports found for patient with ID: {}", patientId); // Logging when reports are found
                return ResponseEntity.status(HttpStatus.OK).body(patientServices.patientReports(patientId));
            }
        } catch (Exception e) {
            logger.error("Error fetching reports for patient ID: {}", patientId, e); // Logging error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
