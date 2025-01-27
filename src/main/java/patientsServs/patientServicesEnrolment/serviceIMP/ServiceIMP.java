package patientsServs.patientServicesEnrolment.serviceIMP;

import java.time.LocalDate;
import java.util.List;

import patientsServs.patientServicesEnrolment.models.Patient;

public interface ServiceIMP {
	
	// Check if patient exists
    boolean isPatientExist(Long patientId);

    // Save patient
    Patient savePatient(Patient patient);

    // Get all patients
    List<Patient> getAllPatients();

    // Get patient by ID
    Patient getPatientById(Long ID);

    // Update patient by ID
    String updatePatient(Long id, Patient patient);

    // Delete patient by ID
    String deletePatient(Long id);

    // Get registration details
    String RegistarationDetails();

    // Get patients by Date of Birth (DOB) range
    List<Patient> byDOB(LocalDate startDate, LocalDate endDate);

    // Get patient by name
    List<Patient> getPatientByName(String patientName);

}
