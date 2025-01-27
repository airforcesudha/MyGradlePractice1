package patientsServs.patientServicesEnrolment.ServiceLayer;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import patientsServs.patientServicesEnrolment.Repositories.DiseaseRepository;
import patientsServs.patientServicesEnrolment.Repositories.MedicationRepository;
import patientsServs.patientServicesEnrolment.Repositories.PatientRepository;
import patientsServs.patientServicesEnrolment.models.Patient;
import patientsServs.patientServicesEnrolment.serviceIMP.ServiceIMP;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PatientsServices implements ServiceIMP{
	
	 // Logger for the PatientsServices class
    private static final Logger logger = LoggerFactory.getLogger(PatientsServices.class); 
    
    
    // injecting required dependiences for this layer
    @Autowired
    PatientRepository patientRep;
	
	@Autowired
	DiseaseRepository diseaseRep;
	
	@Autowired
	MedicationRepository medicationRep;
	
	
	
	
	
	
	// Sample method for testing
    public String sample() {
        logger.info("Sample method called");
        return "sample";
    }
	
	
	
	
	// Check if patient exists
    public boolean isPatientExist(Long patientId) {    
        logger.info("Checking if patient with ID: {} exists", patientId);
        Patient patient = patientRep.findById(patientId).orElse(null);
        if (patient == null) {
            logger.warn("Patient with ID: {} not found", patientId);
            return false;
        } else {
            logger.info("Patient with ID: {} found", patientId);
            return true;
        }
    }
	

    
    
	
    // Save patient
    @Transactional
    public Patient savePatient(Patient patient) {
        logger.info("Saving patient: {}", patient);
        return patientRep.save(patient);
    }

	
	
	
 // Get all patients
    public List<Patient> getAllPatients() {
        logger.info("Fetching all patients");
        return patientRep.findAll();
    }
    
    
    
    
    
 // Get patient by ID
    public Patient getPatientById(Long ID) {
        logger.info("Fetching patient with ID: {}", ID);
        Patient patient = patientRep.findById(ID).orElse(null);
        if (patient == null) {
            logger.warn("Patient with ID: {} not found", ID);
            return null;
        } else {
            logger.info("Patient with ID: {} found", ID);
            return patient;
        }
    }
    
    
    
    
    
 // Update patient by ID
    public String updatePatient(Long id, Patient patient) {
        logger.info("Updating patient with ID: {}", id);
        if (getPatientById(id) == null) {
            logger.warn("Patient with ID: {} not found for update", id);
            return "Patient NOT FOUND";
        } else {
            patientRep.save(patient);
            logger.info("Patient with ID: {} updated successfully", id);
            return "UPDATE SUCCESSFULL";
        }
    }
    
    
    
    
    
 // Delete patient by ID
    public String deletePatient(Long id) {
        logger.info("Deleting patient with ID: {}", id);
        patientRep.deleteById(id);
        logger.info("Patient with ID: {} deleted successfully", id);
        return "DELETED";
    }
    
	
	
	
 // Get registration details (total patients, diseases, medications)
    public String RegistarationDetails() {
        logger.info("Fetching registration details");
        Integer totalPatients = patientRep.findAll().size();
        Integer totalDisease = diseaseRep.findAll().size();
        Integer totalMedicines = medicationRep.findAll().size();
        
        logger.info("Total Patients: {}, Total Diseases: {}, Total Medications: {}", 
                    totalPatients, totalDisease, totalMedicines);
        
        return "TOTAL REGISTERED PATIENTS : " + totalPatients + "\n" 
             + "TOTAL REGISTERED DISEASES : " + totalDisease + "\n"
             + "TOTAL REGISTERED MEDICATIONS : " + totalMedicines;
    }
	
    
    
	
	
    
 // Get patients by Date of Birth (DOB) range
    public List<Patient> byDOB(LocalDate startDate, LocalDate endDate) {
        logger.info("Fetching patients by DOB range: {} to {}", startDate, endDate);
        
        List<Patient> patientsList = patientRep.findPatientsByDOBRange(startDate, endDate);
        
        if (patientsList.isEmpty()) {
            logger.warn("No patients found in the given DOB range: {} to {}", startDate, endDate);
        } else {
            logger.info("Found {} patients in the given DOB range", patientsList.size());
        }
        
        return patientsList;
    }
    
    
    
    
    
    // get Patient by name
    public List<Patient> getPatientByName(String patientName) {
    	
    	logger.info("Fetching patients by Name: {}",patientName);
    	return patientRep.findPatientsByName(patientName);
    
    	
    }

	

}
