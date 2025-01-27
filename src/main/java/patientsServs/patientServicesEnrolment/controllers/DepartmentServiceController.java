package patientsServs.patientServicesEnrolment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import patientsServs.patientServicesEnrolment.DTO.Department_DTO;
import patientsServs.patientServicesEnrolment.ServiceLayer.DepartmentService;
import patientsServs.patientServicesEnrolment.models.Department;

@RestController
public class DepartmentServiceController {

	
	@Autowired
	private DepartmentService deptService;
	
	
	@GetMapping("/departments")
	public ResponseEntity<List<Department_DTO>> departments(){
		return ResponseEntity.status(HttpStatus.OK).body(deptService.departments());
	}
	
	
	@PostMapping("/register_new_department")
	public ResponseEntity<Object> newDepartment(@RequestBody Department dept){
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(deptService.saveNewDepartment(dept));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR : " + e.getMessage());
		}
	}
	
	
	
	
	
}
