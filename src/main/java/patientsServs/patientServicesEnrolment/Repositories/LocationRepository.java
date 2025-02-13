package patientsServs.patientServicesEnrolment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import patientsServs.patientServicesEnrolment.DTO.LocationDTO;
import patientsServs.patientServicesEnrolment.models.Location;


@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{
	
	
	//fetching all locations except some fields
	@Query("SELECT new patientsServs.patientServicesEnrolment.DTO.LocationDTO(l.id, l.state, l.city, l.country, l.pincode) FROM Location l")
    List<LocationDTO> findAllLocationsWithoutHospitals();
}
