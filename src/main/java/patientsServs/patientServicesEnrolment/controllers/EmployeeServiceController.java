package patientsServs.patientServicesEnrolment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import patientsServs.patientServicesEnrolment.ServiceLayer.EmployeeServices;
import patientsServs.patientServicesEnrolment.models.Employee;

@RestController
public class EmployeeServiceController {

	
	@Autowired
	private EmployeeServices empServices;
	
	

	@PostMapping("/new_employee")
	public ResponseEntity<String> new_employee(@RequestBody Employee employee) {
		try {
			empServices.new_employee(employee);
			return ResponseEntity.status(HttpStatus.OK).body("EMPLOYEE SAVED");
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
}
