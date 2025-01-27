package patientsServs.patientServicesEnrolment.ServiceLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import patientsServs.patientServicesEnrolment.DTO.Location_DTO;
import patientsServs.patientServicesEnrolment.Repositories.LocationRepository;
import patientsServs.patientServicesEnrolment.models.Location;
import patientsServs.patientServicesEnrolment.serviceIMP.LocationServiceIMP;


@Service
public class LocationServices implements LocationServiceIMP {
	
	
	@Autowired
	private LocationRepository locationRep;

	@Override
	public Location Add_Location(Location location) {
		return locationRep.save(location);
	}

	
	
	
	public List<Location_DTO> locations(){
		
		return locationRep.findAllLocationsWithoutHospitals();
		
	}

}
