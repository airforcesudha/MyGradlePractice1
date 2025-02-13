package patientsServs.patientServicesEnrolment.serviceLayer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import patientsServs.patientServicesEnrolment.DTO.DepartmentDTO;
import patientsServs.patientServicesEnrolment.models.Department;
import patientsServs.patientServicesEnrolment.repositories.DepartmentRepository;
import patientsServs.patientServicesEnrolment.serviceIMP.DepartmentServiceIMP;

@Service
public class DepartmentService implements DepartmentServiceIMP {

    // Logger instance for logging service layer activities
    private static final Logger logger = LoggerFactory.getLogger(DepartmentService.class);

    // Injecting the repository for database operations
    @Autowired
    private DepartmentRepository deptRep;

    /**
     * Saves a new department entity in the database.
     * @param dept - Department entity to be saved
     * @return Saved department entity
     */
    @Override
    public Department saveNewDepartment(Department dept) {
        logger.info("Attempting to save new department: {}", dept);
        Department savedDepartment = deptRep.save(dept);
        logger.info("Department saved successfully with ID: {}", savedDepartment.getId());
        return savedDepartment;
    }

    /**
     * Retrieves a list of all departments from the database.
     * @return List of DepartmentDTO objects
     */
    @Override
    public List<DepartmentDTO> departments() {
        logger.info("Fetching all departments from the database");
        List<DepartmentDTO> departmentList = deptRep.findAllDepartments();
        logger.info("Total departments fetched: {}", departmentList.size());
        return departmentList;
    }
}
