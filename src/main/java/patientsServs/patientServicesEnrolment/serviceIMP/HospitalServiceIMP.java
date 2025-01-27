package patientsServs.patientServicesEnrolment.serviceIMP;

import java.util.List;

import patientsServs.patientServicesEnrolment.models.Hospital;

public interface HospitalServiceIMP {

	
	// Method to save a new hospital
    Hospital saveNewHospita(Hospital hospital);
    
    // Method to add departments to an existing hospital
    Hospital addDept(long hosp_Id, List<Long> depts_List);
}
