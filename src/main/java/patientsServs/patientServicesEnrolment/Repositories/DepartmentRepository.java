package patientsServs.patientServicesEnrolment.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import patientsServs.patientServicesEnrolment.DTO.DepartmentDTO;
import patientsServs.patientServicesEnrolment.models.Department;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

	
	//to fetching departments details except hospital details
	@Query("SELECT new patientsServs.patientServicesEnrolment.DTO.DepartmentDTO(D.id,D.name) FROM Department D")
    List<DepartmentDTO> findAllDepartments();

}
