package patientsServs.patientServicesEnrolment.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import patientsServs.patientServicesEnrolment.DTO.Location_DTO;
import patientsServs.patientServicesEnrolment.models.Location;


@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{
	
	@Query("SELECT new patientsServs.patientServicesEnrolment.DTO.Location_DTO(l.id, l.state, l.city, l.country, l.pincode) FROM Location l")
    List<Location_DTO> findAllLocationsWithoutHospitals();
}
