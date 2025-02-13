package patientsServs.patientServicesEnrolment.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import patientsServs.patientServicesEnrolment.models.Disease;
import patientsServs.patientServicesEnrolment.serviceLayer.DiseaseService;

@RestController
@RequestMapping("/api")
public class DiseaseServiceController {

    // Logger to log messages at different levels (INFO, ERROR, etc.)
    private static final Logger logger = LoggerFactory.getLogger(DiseaseServiceController.class);

    @Autowired
    private DiseaseService diseaseService;

    // Endpoint to get disease by ID
    @GetMapping("/disease/{diseaseId}")
    public ResponseEntity<Object> getDisease(@PathVariable("diseaseId") Long diseaseId) {
        logger.info("Entered getDisease method with diseaseId: {}", diseaseId); // Log method entry and diseaseId

        Disease disease = diseaseService.getDisease(diseaseId);

        if (disease == null) {
            logger.warn("Disease not found with ID: {}", diseaseId); // Log a warning if the disease is not found
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("DISEASE NOT FOUND ON GIVEN ID");
        } else {
            logger.info("Returning disease details for diseaseId: {}", diseaseId); // Log method exit with disease ID
            return ResponseEntity.status(HttpStatus.OK).body(diseaseService.getDisease(diseaseId));
        }
    }

    // Endpoint to get all diseases
    @GetMapping("/diseases")
    public ResponseEntity<Object> diseases() {
        logger.info("Entered diseases method"); // Log method entry

        List<Disease> diseasesList = diseaseService.getAllDiseases();
        if (diseasesList.isEmpty()) {
            logger.info("No diseases found in the system"); // Log if no diseases are found
            return ResponseEntity.status(HttpStatus.OK).body("NO DISEASE : DISEASES NOT REGISTRED");
        } else {
            logger.info("Returning list of {} diseases", diseasesList.size()); // Log number of diseases returned
            return ResponseEntity.status(HttpStatus.OK).body(diseasesList);
        }
    }

    // Endpoint to get diseases by name
    @GetMapping("/diseaseByName/{diseaseName}")
    public ResponseEntity<Object> getDiseaseByName(@PathVariable("diseaseName") String diseaseName) {
        logger.info("Entered getDiseaseByName method with diseaseName: {}", diseaseName); // Log method entry and disease name

        List<Disease> diseasesList = diseaseService.getDiseasesByName(diseaseName);
        if (diseasesList.isEmpty()) {
            logger.warn("No diseases found matching the name: {}", diseaseName); // Log if no diseases match the name
            return ResponseEntity.status(HttpStatus.OK).body("DISEASES NOT MATCH WITH GIVEN NAME " + diseaseName);
        } else {
            logger.info("Returning {} diseases matching the name: {}", diseasesList.size(), diseaseName); // Log number of matching diseases
            return ResponseEntity.status(HttpStatus.OK).body(diseasesList);
        }
    }
}
