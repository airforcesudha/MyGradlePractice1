package patientsServs.patientServicesEnrolment.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import patientsServs.patientServicesEnrolment.DTO.DepartmentDTO;
import patientsServs.patientServicesEnrolment.models.Department;
import patientsServs.patientServicesEnrolment.serviceLayer.DepartmentService;

@RestController
@RequestMapping("/api")
public class DepartmentServiceController {

    // Logger to log messages at different levels (INFO, ERROR, etc.)
    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceController.class);

    @Autowired
    private DepartmentService departmentService;

    // Endpoint to get all departments
    @GetMapping("/departments")
    public ResponseEntity<List<DepartmentDTO>> departments() {
        // Log the entry into the method
        logger.info("Entered departments method");

        // Call the service method to fetch the list of departments
        List<DepartmentDTO> departmentList = departmentService.departments();

        // Log the exit from the method, including the size of the list of departments
        logger.info("Exiting departments method with {} departments", departmentList.size());

        // Return the list of departments as the response
        return ResponseEntity.status(HttpStatus.OK).body(departmentList);
    }

    
    
    
    // Endpoint to register a new department
    @PostMapping("/department")
    public ResponseEntity<Object> newDepartment(@RequestBody Department dept) {
        // Log the entry into the method and include the department name (parameter received)
        logger.info("Entered newDepartment method with department name: {}", dept.getName());

        try {
            // Call the service method to save the new department
            Object savedDepartment = departmentService.saveNewDepartment(dept);

            // Log the successful department creation, including the department ID
            logger.info("Successfully saved new department with ID: {}", ((Department) savedDepartment).getId());

            // Return the saved department as the response
            return ResponseEntity.status(HttpStatus.OK).body(savedDepartment);

        } catch (Exception e) {
            // Log any error that occurred during the department creation process
            logger.error("Error occurred while saving department: {}", e.getMessage(), e);

            // Return a BAD_REQUEST response with the error message
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR : " + e.getMessage());
        }
    }
}
