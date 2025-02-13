package patientsServs.patientServicesEnrolment.repositories;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import patientsServs.patientServicesEnrolment.models.Patient;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
	
	//fetching patients by name with starting letters matching
	@Query("SELECT p FROM Patient p WHERE LOWER(p.name) LIKE LOWER(CONCAT(:patientName, '%'))")
    List<Patient> findPatientsByName(@Param("patientName") String patientName);
	
	
	//fetching patients by DOB with specific range
	@Query("SELECT p FROM Patient p WHERE p.dateOfBirth BETWEEN :startDate AND :endDate")
    List<Patient> findPatientsByDOBRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
