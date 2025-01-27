package patientsServs.patientServicesEnrolment.serviceIMP;

import java.util.List;

import patientsServs.patientServicesEnrolment.DTO.Location_DTO;
import patientsServs.patientServicesEnrolment.models.Location;

public interface LocationServiceIMP {
	
	Location Add_Location(Location location);
	
	List<Location_DTO> locations();

}
