//package patientsServs.patientServicesEnrolment.controllers;
//
//
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import patientsServs.patientServicesEnrolment.ServiceLayer.PatientsServices;
//import patientsServs.patientServicesEnrolment.models.Patient;
//
//@RestController
//public class sampleController {
//	
//	@Autowired
//	PatientsServices services;
//	
//	
//	
//	
//	
//	@PostMapping("/savePatient")
//    public ResponseEntity<Object> savePatient(@RequestBody Patient patient) {
//		return ResponseEntity.status(HttpStatus.CREATED).body(services.savePatient(patient));
//    }
//	
//	
//	
//	
//	@GetMapping("/Patients")
//	public ResponseEntity<List<Patient>> getAllPatients(){
//		return ResponseEntity.status(HttpStatus.OK).body(services.getAllPatients());
//	}
//	
//	
//	
//	
//	
//	
//	@GetMapping("PatientByID/{id}")
//    public ResponseEntity<Object> getPatientById(@PathVariable("id") Long id) {
//		
//		Patient patient = services.getPatientById(id);
//		if(patient == null) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Patient ID : " + id + " Not Found");
//		}else {
//			return ResponseEntity.status(HttpStatus.OK).body(patient);
//					
//		}
//	
//	}
//	
//	
//	
//	
//	@PutMapping("/updatePatientById/{id}")
//	public ResponseEntity<String> updatePatient(@PathVariable("id") Long id,@RequestBody Patient patient) {
//	    //System.out.println("1");
//	    String status = services.updatePatient(id, patient);
//	    //System.out.println("2");
//
//	    if(status.equalsIgnoreCase("Patient NOT FOUND")) {
//	        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("PATIENT NOT FOUND");
//	    } else {
//	        return ResponseEntity.status(HttpStatus.OK).body("UPDATE SUCCESSFULLY");
//	    }
//	}
//	
//	
//	
//	@GetMapping("/getRegistrationData")
//	public ResponseEntity<String> getNoOfPatients(){
//		return ResponseEntity.status(HttpStatus.OK).body(services.RegistarationDetails());
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	@DeleteMapping("/deletePatientById/{id}")
//	public ResponseEntity<String> DeletePatient(@PathVariable("id") Long id) {
//		
//		if(!services.isPatientExist(id)) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("PATIENT NOT FOUND");
//		}else {
//			services.deletePatient(id);
//			return ResponseEntity.status(HttpStatus.OK).body(services.deletePatient(id));
//		}
//		
//	}
//	
//	
//	
//	@GetMapping("/by-DOB/{startDate}/{endDate}")
//	public ResponseEntity<Object> byDOB(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate){
//		
//		if(services.byDOB(startDate, endDate).size() == 0) {
//			return ResponseEntity.status(HttpStatus.OK).body("NO PATIENTS AT GIVEN DATE OF BIRTH RANGE.");
//		}else {
//			return ResponseEntity.status(HttpStatus.OK).body(services.byDOB(startDate, endDate));
//		}
//		
//		
//		
//	}
//
//
//}
