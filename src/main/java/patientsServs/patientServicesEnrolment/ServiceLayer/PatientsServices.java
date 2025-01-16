package patientsServs.patientServicesEnrolment.ServiceLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import patientsServs.patientServicesEnrolment.Repositories.DiseaseRepository;
import patientsServs.patientServicesEnrolment.Repositories.MedicationRepository;
import patientsServs.patientServicesEnrolment.Repositories.PatientRepository;
import patientsServs.patientServicesEnrolment.models.Patient;

@Service
public class PatientsServices {
	
	
	@Autowired
	PatientRepository patientRep;
	
	@Autowired
	DiseaseRepository diseaseRep;
	
	@Autowired
	MedicationRepository medicationRep;
	
	
	
	public String sample() {
		return "sample";
	}
	
	
	// checking patient 
	public boolean isPatientExist(Long patientId) {	
		Patient patient = patientRep.findById(patientId).orElse(null);
		if (patient == null) {
			return false;
		}else {
			return true;
		}
	}
	
	
	public Patient savePatient(Patient patient) {
        return patientRep.save(patient);
    }

	
    public List<Patient> getAllPatients() {
        return patientRep.findAll();
    }
    
    
    public Patient getPatientById(Long ID) {
    	Patient patient = patientRep.findById(ID).orElse(null);
    	if(patient == null) {
    		return null;
    	}else {
    		return patient;
    	}
    }
    
    
    
    
    
    public String updatePatient(Long id, Patient patient) {
    	
    	if(getPatientById(id) == null) {
    		return "Patient NOT FOUND";
    	}else {
    		patientRep.save(patient);
    		return "UPDATE SUCCESSIFULL";
    	}
    	
    }
    
    
    
    public String deletePatient(Long id) {
    	patientRep.deleteById(id);
    	return "DELETED";
    }
    
	
	
	
	
	
	
	

}
