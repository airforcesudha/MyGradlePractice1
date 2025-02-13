package patientsServs.patientServicesEnrolment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import patientsServs.patientServicesEnrolment.DTO.EmployeeRegisterDTO;
import patientsServs.patientServicesEnrolment.excelGenerator.EmployeeExcelGenerator;
import patientsServs.patientServicesEnrolment.serviceLayer.EmployeeServices;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ExportExcelServices {
	
	
	@Autowired
	private EmployeeServices employeeService;
	
	@Autowired
	private EmployeeExcelGenerator excelGenerator;
	
	
	@GetMapping("/employees/excelSheet")
	public void getEmployeesExcelSheet(HttpServletResponse response) throws IOException{
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
        String filename = "students_" + currentDateTime + ".xlsx";

        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        
        
        List<EmployeeRegisterDTO> employeesList = employeeService.getAllEmployees();
        
        excelGenerator = new EmployeeExcelGenerator(employeesList);
        excelGenerator.generateExcel(response);

	}

}
