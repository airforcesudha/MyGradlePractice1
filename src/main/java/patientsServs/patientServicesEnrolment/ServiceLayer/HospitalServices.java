package patientsServs.patientServicesEnrolment.ServiceLayer;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jakarta.transaction.Transactional;
import patientsServs.patientServicesEnrolment.Repositories.DepartmentRepository;
import patientsServs.patientServicesEnrolment.Repositories.HospitalRepository;
import patientsServs.patientServicesEnrolment.models.Department;
import patientsServs.patientServicesEnrolment.models.Hospital;

@Service
public class HospitalServices {

	
	@Autowired
	private HospitalRepository hospitalRep;
	
	@Autowired
	private DepartmentRepository deptRep;
	
	
	public Hospital saveNewHospita(Hospital hospital) {
		return hospitalRep.save(hospital);
	}
	
	public Department saveNewDepartment(Department dept) {
		return deptRep.save(dept);
	}
	
	
	
	@Transactional
	public Hospital addDept(long hosp_Id, List<Long> depts_List) {
		
		Hospital hospital = hospitalRep.findById(hosp_Id).orElseThrow(() -> new NoSuchElementException("HOSPITAL NOT FOUND WITH ID: " + hosp_Id));
		
		for(long dept_id : depts_List) {
			Department dept = deptRep.findById(dept_id).orElseThrow(() -> new NoSuchElementException("DEPARTMENT NOT FOUND WITH ID : " + dept_id));
			dept.getHospitals().add(hospital);
			hospital.getDepartments().add(dept);
		}
		
		return hospital;

	}
	
	

	
}
