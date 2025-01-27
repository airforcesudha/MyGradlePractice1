package patientsServs.patientServicesEnrolment.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import patientsServs.patientServicesEnrolment.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	
	
}
