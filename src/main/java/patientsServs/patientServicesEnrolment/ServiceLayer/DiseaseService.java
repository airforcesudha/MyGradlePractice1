package patientsServs.patientServicesEnrolment.serviceLayer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import patientsServs.patientServicesEnrolment.models.Disease;
import patientsServs.patientServicesEnrolment.models.Medication;
import patientsServs.patientServicesEnrolment.repositories.DiseaseRepository;
import patientsServs.patientServicesEnrolment.serviceIMP.DiseaseServiceIMP;

@Service
public class DiseaseService implements DiseaseServiceIMP {

    // Logger instance for logging service layer activities
    private static final Logger logger = LoggerFactory.getLogger(DiseaseService.class);

    // Injecting the repository for database operations
    @Autowired
    private DiseaseRepository diseaseRep;

    /**
     * Registers a new disease entity in the database.
     * @param disease - Disease entity to be saved
     * @param medications - List of medications associated with the disease (not yet persisted in this method)
     * @return Saved disease entity
     */
    @Override
    public Disease registerDisease(Disease disease, List<Medication> medications) {
        logger.info("Registering new disease: {}", disease);
        Disease savedDisease = diseaseRep.save(disease);
        logger.info("Disease registered successfully with ID: {}", savedDisease.getId());
        return savedDisease;
    }

    /**
     * Retrieves a list of all diseases from the database.
     * @return List of Disease entities
     */
    @Override
    public List<Disease> getAllDiseases() {
        logger.info("Fetching all diseases from the database");
        List<Disease> diseaseList = diseaseRep.findAll();
        logger.info("Total diseases fetched: {}", diseaseList.size());
        return diseaseList;
    }

    /**
     * Retrieves a disease by its ID.
     * @param diseaseId - ID of the disease to be retrieved
     * @return Disease entity if found, otherwise null
     */
    @Override
    public Disease getDisease(Long diseaseId) {
        logger.info("Fetching disease with ID: {}", diseaseId);
        Disease disease = diseaseRep.findById(diseaseId).orElse(null);
        if (disease != null) {
            logger.info("Disease found: {}", disease);
        } else {
            logger.warn("No disease found with ID: {}", diseaseId);
        }
        return disease;
    }

    /**
     * Retrieves diseases by name.
     * @param diseaseName - Name of the disease to search for
     * @return List of Disease entities matching the given name
     */
    @Override
    public List<Disease> getDiseasesByName(String diseaseName) {
        logger.info("Fetching diseases with name: {}", diseaseName);
        List<Disease> diseaseList = diseaseRep.findDiseasesByName(diseaseName);
        logger.info("Total diseases found with name '{}': {}", diseaseName, diseaseList.size());
        return diseaseList;
    }
}
