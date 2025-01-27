package patientsServs.patientServicesEnrolment.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import patientsServs.patientServicesEnrolment.DTO.AddDeptListRequest;
import patientsServs.patientServicesEnrolment.ServiceLayer.HospitalServices;
import patientsServs.patientServicesEnrolment.models.Hospital;

@RestController
public class HospitalServicesController {

	@Autowired
	private HospitalServices hospitalService;
	
	
	@PostMapping("/new_hospital")
	public ResponseEntity<String> newHospital(@RequestBody Hospital hospital){
		
		try {
			System.out.println(hospital.getDepartments());
			hospitalService.saveNewHospita(hospital);
			return ResponseEntity.status(HttpStatus.OK).body("SUCCESSFULLY HOSPITAL REGISTRED.");
			
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR : GIVEN LOCATION ID : " + hospital.getLocation().getId() + " WAS NOT FOUND");
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR : " + e.getMessage());
		}
		
	}
	
	
	@PostMapping("/add_department/{hospital_id}")
	public ResponseEntity<Object> addDepartmnet(@PathVariable("hospital_id") long hospital_id,@RequestBody AddDeptListRequest dList){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(hospitalService.addDept(hospital_id,dList.getDepts_ids()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
	

	
}
