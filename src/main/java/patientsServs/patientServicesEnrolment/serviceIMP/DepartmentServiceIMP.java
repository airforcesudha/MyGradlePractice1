package patientsServs.patientServicesEnrolment.serviceIMP;

import java.util.List;

import patientsServs.patientServicesEnrolment.DTO.Department_DTO;
import patientsServs.patientServicesEnrolment.models.Department;

public interface DepartmentServiceIMP {
	
	 // Method to save a new department
    Department saveNewDepartment(Department dept);

    // Method to get the list of all departments
    List<Department_DTO> departments();

}
