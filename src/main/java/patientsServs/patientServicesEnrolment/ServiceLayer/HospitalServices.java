package patientsServs.patientServicesEnrolment.serviceLayer;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import patientsServs.patientServicesEnrolment.models.Department;
import patientsServs.patientServicesEnrolment.models.Hospital;
import patientsServs.patientServicesEnrolment.repositories.DepartmentRepository;
import patientsServs.patientServicesEnrolment.repositories.HospitalRepository;
import patientsServs.patientServicesEnrolment.serviceIMP.HospitalServiceIMP;

@Service
public class HospitalServices implements HospitalServiceIMP {

    // Logger instance for logging service activities
    private static final Logger logger = LoggerFactory.getLogger(HospitalServices.class);

    @Autowired
    private HospitalRepository hospitalRep;

    @Autowired
    private DepartmentRepository deptRep;

    /**
     * Saves a new hospital record.
     * @param hospital - Hospital entity to be saved.
     * @return Saved hospital entity.
     */
    @Override
    public Hospital saveNewHospita(Hospital hospital) {
        logger.info("Saving new hospital: {}", hospital.getName());
        return hospitalRep.save(hospital);
    }

    /**
     * Adds multiple departments to an existing hospital.
     * @param hosp_Id - ID of the hospital to which departments will be added.
     * @param depts_List - List of department IDs to be associated with the hospital.
     * @return Updated hospital entity after adding departments.
     */
    @Override
    @Transactional
    public Hospital addDept(long hosp_Id, List<Long> depts_List) {
        logger.info("Adding departments {} to hospital with ID: {}", depts_List, hosp_Id);

        // Fetch hospital, throw exception if not found
        Hospital hospital = hospitalRep.findById(hosp_Id)
                .orElseThrow(() -> {
                    logger.error("Hospital not found with ID: {}", hosp_Id);
                    return new NoSuchElementException("HOSPITAL NOT FOUND WITH ID: " + hosp_Id);
                });

        // Iterate through department IDs and associate them with the hospital
        for (long dept_id : depts_List) {
            Department dept = deptRep.findById(dept_id)
                    .orElseThrow(() -> {
                        logger.error("Department not found with ID: {}", dept_id);
                        return new NoSuchElementException("DEPARTMENT NOT FOUND WITH ID: " + dept_id);
                    });

            // Establish bi-directional relationship
            dept.getHospitals().add(hospital);
            hospital.getDepartments().add(dept);
        }

        logger.info("Successfully added departments to hospital ID: {}", hosp_Id);
        return hospital;
    }

    /**
     * Retrieves a hospital by its ID.
     * @param hospitalId - The ID of the hospital to fetch.
     * @return Hospital entity if found, else throws an exception.
     */
    @Override
    public Hospital getHospital(Long hospitalId) {
        logger.info("Fetching hospital with ID: {}", hospitalId);
        return hospitalRep.findById(hospitalId)
                .orElseThrow(() -> {
                    logger.error("Hospital not found with ID: {}", hospitalId);
                    return new NoSuchElementException("HOSPITAL NOT FOUND WITH GIVEN ID - " + hospitalId);
                });
    }

    /**
     * Retrieves hospitals by location ID.
     * @param locationId - The location ID to filter hospitals.
     * @return List of hospitals in the specified location.
     */
    @Override
    public List<Hospital> getHospitalByLocationId(Long locationId) {
        logger.info("Fetching hospitals in location ID: {}", locationId);
        return hospitalRep.findHospitalsByLocationId(locationId);
    }

    /**
     * Retrieves hospitals by name.
     * @param hospitalName - The name of the hospital(s) to fetch.
     * @return List of hospitals matching the name.
     */
    @Override
    public List<Hospital> getHospitalByName(String hospitalName) {
        logger.info("Fetching hospitals with name: {}", hospitalName);
        return hospitalRep.findHospitalsByName(hospitalName);
    }
}
