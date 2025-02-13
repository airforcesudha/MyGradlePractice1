package patientsServs.patientServicesEnrolment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import patientsServs.patientServicesEnrolment.models.Disease;


@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long>{
	
	@Query("SELECT d FROM Disease d WHERE LOWER(d.name) LIKE LOWER(CONCAT(:diseaseName, '%'))")
	List<Disease> findDiseasesByName(@Param("diseaseName") String diseaseName);


}
