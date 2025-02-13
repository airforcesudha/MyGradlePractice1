package patientsServs.patientServicesEnrolment.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import patientsServs.patientServicesEnrolment.DTO.EmployeeRegisterDTO;
import patientsServs.patientServicesEnrolment.DTO.NewEmployeeDeptDTO;
import patientsServs.patientServicesEnrolment.serviceLayer.EmployeeServices;

@RestController
@RequestMapping("/api")
public class EmployeeServiceController {

    // Logger to log messages at different levels (INFO, ERROR, etc.)
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceController.class);

    @Autowired
    private EmployeeServices employeeServices;

    // Endpoint to register a new doctor
    @PostMapping("/doctor")
    public ResponseEntity<String> registerDoctor(@RequestBody NewEmployeeDeptDTO details) {
    	
    	
    	// checking mobileNo & eMailId 
    	if(employeeServices.isMobileallreadyExist(details.getMobileNo())) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("GIVEN MOBILE NO ALLREADY EXIST");
    	}else if(employeeServices.iseMailExist(details.geteMailId())) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("GIVEN EMAIL ID WAS ALLREADY EXIST");
    	}
    	
    	
    	
        // Log method entry and the details of the doctor being registered (for debugging)
        logger.info("Entered registerDoctor method with doctor details: {}", details);

        try {
            // Call the service method to add a new doctor
            String result = employeeServices.addDoctor(details);

            // Log successful registration of the doctor
            logger.info("Successfully registered doctor with details: {}", details);

            // Return success response
            return ResponseEntity.status(HttpStatus.OK).body(result);

        } catch (Exception e) {
            // Log the error message and exception stack trace
            logger.error("Error occurred while registering doctor: {}", e.getMessage(), e);

            // Return error response with exception message
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    
    
    // getting all employees list
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeRegisterDTO>> getAllEmployees(){
    	
    	List<EmployeeRegisterDTO> employeesList = employeeServices.getAllEmployees();
    	
    	return ResponseEntity.status(HttpStatus.OK).body(employeesList);
    }
    
    
    //fetching  employees by Hospital Id
    @GetMapping("/employeesByHospitalId/{hospitalId}")
    public ResponseEntity<Object> getEmpployeesByHospitalId(@PathVariable("hospitalId") Long hospitalId){
    	
    	return ResponseEntity.status(HttpStatus.OK).body(employeeServices.getEmployessByHospitalId(hospitalId));
    	
    }
    
    
    
    @GetMapping("/isMobileNoExist/{mobileNo}")
    public boolean isMobileNoExist(@PathVariable("mobileNo") String mobileNo) {
    	return employeeServices.isMobileallreadyExist(mobileNo);
    }
    
    
}
