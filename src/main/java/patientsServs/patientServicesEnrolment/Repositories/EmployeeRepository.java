package patientsServs.patientServicesEnrolment.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import patientsServs.patientServicesEnrolment.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	@Query("SELECT e FROM Employee e WHERE e.hospital.id = :hospitalId")
    List<Employee> findEmployeesByHospitalId(@Param("hospitalId") Long hospitalId);
	
	@Query("SELECT e FROM Employee e WHERE e.eMailId = : eMailId")
	Employee findByEMailId(String eMailId);

	 // Custom query using JPQL
    @Query("SELECT e FROM Employee e WHERE e.mobileNo = :mobileNo")
    Employee findByMobileNo(@Param("mobileNo") String mobileNo);
	
}
