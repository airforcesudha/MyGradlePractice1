package patientsServs.patientServicesEnrolment.ServiceLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import patientsServs.patientServicesEnrolment.DTO.Department_DTO;
import patientsServs.patientServicesEnrolment.Repositories.DepartmentRepository;
import patientsServs.patientServicesEnrolment.models.Department;
import patientsServs.patientServicesEnrolment.serviceIMP.DepartmentServiceIMP;

@Service
public class DepartmentService implements DepartmentServiceIMP{

	@Autowired
	private DepartmentRepository deptRep;
	
	
	@Override
	public Department saveNewDepartment(Department dept) {
		return deptRep.save(dept);
	}
	
	
	@Override
	public List<Department_DTO> departments(){
		return deptRep.findAllDepartments();
	}
	
}
