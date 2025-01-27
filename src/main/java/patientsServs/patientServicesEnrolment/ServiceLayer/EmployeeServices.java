package patientsServs.patientServicesEnrolment.ServiceLayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.transaction.Transactional;
import patientsServs.patientServicesEnrolment.Repositories.DepartmentRepository;
import patientsServs.patientServicesEnrolment.Repositories.EmployeeRepository;
import patientsServs.patientServicesEnrolment.Repositories.HospitalRepository;
import patientsServs.patientServicesEnrolment.models.Department;
import patientsServs.patientServicesEnrolment.models.Employee;
import patientsServs.patientServicesEnrolment.models.Hospital;

@Service
public class EmployeeServices {
	
	@Autowired
	private EmployeeRepository empRep;
	
	@Autowired
	private HospitalRepository hospitalRep;
	
	@Autowired
	private DepartmentRepository deptRep;

	@Transactional
	public Employee new_employee(Employee employee) {
		
		Hospital hospital = hospitalRep.findById(employee.getHospital().getId()).orElseThrow(() -> new NoSuchElementException("HOSPITAL NOT FOUND ON THIS ID : " + employee.getHospital().getId()));
		
		boolean isDeptExistInHospital = false;
		
		for (Department current_dept : hospital.getDepartments()) {
			
			if(current_dept.getId() == employee.getDepartment().getId()) {
				isDeptExistInHospital = true;
				break;
			}
		}
		
		if(isDeptExistInHospital) {
			return empRep.save(employee);
		}else {
			throw new NoSuchElementException("DEPARTMENT NOT FOUND IN RESPECTIVE HOSPITAL");
		}
		
		
	}

}
