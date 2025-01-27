package patientsServs.patientServicesEnrolment.Repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import patientsServs.patientServicesEnrolment.DTO.Department_DTO;
import patientsServs.patientServicesEnrolment.models.Department;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

	@Query("SELECT new patientsServs.patientServicesEnrolment.DTO.Department_DTO(D.id,D.name) FROM Department D")
    List<Department_DTO> findAllDepartments();

}
