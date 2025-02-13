package patientsServs.patientServicesEnrolment.serviceIMP;

import java.util.List;

import patientsServs.patientServicesEnrolment.DTO.LocationDTO;
import patientsServs.patientServicesEnrolment.models.Location;

public interface LocationServiceIMP {

	Location Add_Location(Location location);

	List<LocationDTO> locations();

}
