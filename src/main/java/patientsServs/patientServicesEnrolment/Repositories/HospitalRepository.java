package patientsServs.patientServicesEnrolment.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import patientsServs.patientServicesEnrolment.models.Hospital;


@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long>{

	@Query("SELECT h FROM Hospital h JOIN h.departments d WHERE h.id = :hospitalId AND d.id = :departmentId")
	Optional<Hospital> findHospitalByIdAndDepartmentId(@Param("hospitalId") Long hospitalId, @Param("departmentId") Long departmentId);

	
	@Query("SELECT h FROM Hospital h WHERE h.location.id = :locationId")
	List<Hospital> findHospitalsByLocationId(@Param("locationId") Long locationId);

	
	@Query("SELECT h FROM Hospital h WHERE LOWER(h.name) LIKE LOWER(CONCAT(:hospitalName, '%'))")
	List<Hospital> findHospitalsByName(@Param("hospitalName") String hospitalName);


	
}
