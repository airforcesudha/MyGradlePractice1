package patientsServs.patientServicesEnrolment.serviceIMP;

import java.util.List;

import patientsServs.patientServicesEnrolment.DTO.DepartmentDTO;
import patientsServs.patientServicesEnrolment.models.Department;

public interface DepartmentServiceIMP {
	
	 // Method to save a new department
    Department saveNewDepartment(Department dept);

    // Method to get the list of all departments
    List<DepartmentDTO> departments();

}
