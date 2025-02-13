package patientsServs.patientServicesEnrolment.serviceIMP;

import java.util.List;

import patientsServs.patientServicesEnrolment.DTO.EmployeeRegisterDTO;
import patientsServs.patientServicesEnrolment.DTO.NewEmployeeDeptDTO;
import patientsServs.patientServicesEnrolment.models.Employee;

public interface EmployeeServiceIMP {

	 // Method to create a new employee
    Employee new_employee(Employee employee);

	String addDoctor(NewEmployeeDeptDTO details);

	List<EmployeeRegisterDTO> getAllEmployees();
}
