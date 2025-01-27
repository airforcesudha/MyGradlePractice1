package patientsServs.patientServicesEnrolment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import patientsServs.patientServicesEnrolment.ServiceLayer.LocationServices;
import patientsServs.patientServicesEnrolment.models.Location;


@RestController
public class LocationServicesController {

	
	@Autowired
	private LocationServices locationServices;
	
	
	
	//register a new location
	@PostMapping("/new_location")
	public ResponseEntity<String> newLocation(@RequestBody Location location) {
		
		if(location == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("INVALID DATA TO ADD LOCATION");
		}else {
			try {
				locationServices.Add_Location(location);
				return ResponseEntity.status(HttpStatus.OK).body("NEW LOCATION WAS SUCCESSFULLY ADDED");
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR : " + e.getMessage());
			}
		}
	}
	

	
	//fetching all locations
	@GetMapping("/locations")
	public ResponseEntity<Object> locations(){
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(locationServices.locations());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
	
}
