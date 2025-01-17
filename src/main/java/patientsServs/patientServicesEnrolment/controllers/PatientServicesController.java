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

import patientsServs.patientServicesEnrolment.ServiceLayer.PatientsServices;
import patientsServs.patientServicesEnrolment.models.Patient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/patients")
public class PatientServicesController {

	// Logger for the controller
	private static final Logger logger = LoggerFactory.getLogger(PatientServicesController.class);

	// injecting required dependiences into the PatientServicesController
	@Autowired
	PatientsServices services;

	// Save a new patient
	@PostMapping("/save_New_Patient")
	public ResponseEntity<Object> savePatient(@RequestBody Patient patient) {
		logger.info("Attempting to save a new patient: {}", patient); // the attempt to save patient
		return ResponseEntity.status(HttpStatus.CREATED).body(services.savePatient(patient));
	}
	
	
	

	// Get all patients
	@GetMapping("/Patients")
	public ResponseEntity<List<Patient>> getAllPatients() {
		logger.info("Fetching all patients"); // the request for all patients
		return ResponseEntity.status(HttpStatus.OK).body(services.getAllPatients());
	}
	
	
	

	// Get a patient by ID
	@GetMapping("Patient_By_ID/{id}")
	public ResponseEntity<Object> getPatientById(@PathVariable("id") Long id) {
		logger.info("Fetching patient with ID: {}", id); // the request to fetch patient by ID
		Patient patient = services.getPatientById(id);
		if (patient == null) {
			logger.warn("Patient ID {} not found", id); // the warning if the patient is not found
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Patient ID : " + id + " Not Found");
		} else {
			logger.info("Patient with ID {} found", id); // successful retrieve
			return ResponseEntity.status(HttpStatus.OK).body(patient);

		}

	}
	
	
	

	// Update patient details by ID
	@PutMapping("/update_Patient_By_Id/{id}")
	public ResponseEntity<String> updatePatient(@PathVariable("id") Long id, @RequestBody Patient patient) {
		logger.info("Attempting to update patient with ID: {}", id); // the attempt to update patient
		String status = services.updatePatient(id, patient);
		// System.out.println("2");

		if (status.equalsIgnoreCase("Patient NOT FOUND")) {
			logger.error("Patient with ID {} not found for update", id); // error if patient is not found
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("PATIENT NOT FOUND");
		} else {
			logger.info("Patient with ID {} updated successfully", id); // successful update
			return ResponseEntity.status(HttpStatus.OK).body("UPDATE SUCCESSFULLY");
		}
	}
	
	
	

	// Get the Overall Information like Total No of patients , disease and medicines
	// registered
	@GetMapping("/All_Registrations_Data")
	public ResponseEntity<String> getNoOfPatients() {
		logger.info("Fetching the total number of registered patients"); // the request for registration data
		return ResponseEntity.status(HttpStatus.OK).body(services.RegistarationDetails());
	}
	
	
	

	// Delete patient by ID
	@DeleteMapping("/delete_Patient_ById/{id}")
	public ResponseEntity<String> DeletePatient(@PathVariable("id") Long id) {
		logger.info("Attempting to delete patient with ID: {}", id); // the attempt to delete patient
		if (!services.isPatientExist(id)) {
			logger.warn("Patient with ID {} not found for deletion", id); // warning if patient doesn't exist
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("PATIENT NOT FOUND");
		} else {
			services.deletePatient(id);
			logger.info("Patient with ID {} deleted successfully", id); // successful deletion
			return ResponseEntity.status(HttpStatus.OK).body(services.deletePatient(id));
		}

	}
	
	
	

	// Delete patient by ID
	@GetMapping("/by_Date_Of_Birth/{startDate}/{endDate}")
	public ResponseEntity<Object> byDOB(@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate) {

		logger.info("Fetching patients by DOB range: {} - {}", startDate, endDate); // the request for DOB range

		// checking date of birth like starting date must be less than ending date
		LocalDate startingDate = LocalDate.parse(startDate);
		LocalDate endingDate = LocalDate.parse(endDate);

		if (startingDate.isBefore(endingDate)) {

			if (services.byDOB(startDate, endDate).size() == 0) {
				logger.info("No patients found for the given DOB range"); // if no patients are found
				return ResponseEntity.status(HttpStatus.OK).body("NO PATIENTS AT GIVEN DATE OF BIRTH RANGE.");
			} else {
				logger.info("Patients found in the given DOB range"); // if patients are found
				return ResponseEntity.status(HttpStatus.OK).body(services.byDOB(startDate, endDate));
			}
		} else {
			logger.info("Given Date Range Invalid."); // if invalid date range
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("INVALID DATE RANGE, GIVEN STARTING DATE MUST BE SMALLER THAN ENDING DATE");
		}

	}
	
	
	
	
	// fetch patient details by name
	@GetMapping("/byName/{patientName}")
	public ResponseEntity<Object> getPatientByName(@PathVariable("patientName") String patientName){
		
		if(services.getPatientByName(patientName).size() != 0) {
			return ResponseEntity.status(HttpStatus.OK).body(services.getPatientByName(patientName));
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PATIENTS NOT FOUND ON GIVEN NAME : " + patientName);
		}
		
	}

}
