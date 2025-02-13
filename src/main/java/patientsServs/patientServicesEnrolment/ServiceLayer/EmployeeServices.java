package patientsServs.patientServicesEnrolment.serviceLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import patientsServs.patientServicesEnrolment.DTO.EmployeeRegisterDTO;
import patientsServs.patientServicesEnrolment.DTO.NewEmployeeDeptDTO;
import patientsServs.patientServicesEnrolment.models.Doctor;
import patientsServs.patientServicesEnrolment.models.Employee;
import patientsServs.patientServicesEnrolment.models.Hospital;
import patientsServs.patientServicesEnrolment.repositories.DepartmentRepository;
import patientsServs.patientServicesEnrolment.repositories.DoctorRepository;
import patientsServs.patientServicesEnrolment.repositories.EmployeeRepository;
import patientsServs.patientServicesEnrolment.repositories.HospitalRepository;
import patientsServs.patientServicesEnrolment.serviceIMP.EmployeeServiceIMP;

@Service
public class EmployeeServices implements EmployeeServiceIMP {
    
    // Logger instance for logging service layer activities
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServices.class);

    // Injecting the repositories for database operations
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;
    
    
    
    
    public Boolean isMobileallreadyExist(String mobileNo) {
    	Employee employee = employeeRepository.findByMobileNo(mobileNo);
    	
    	if(employee == null) {
    		return false;
    	}else {
    		return true;
    	}
    }
    
    
    public Boolean iseMailExist(String eMailId) {
    	Employee employee =  employeeRepository.findByEMailId(eMailId);
    	if(employee == null) {
    		return false;
    	}else {
    		return true;
    	}
    }
    
    
    
    
    

    /**
     * Registers a new employee and associates them with a hospital and department.
     * @param employee - Employee entity containing details such as hospital and department.
     * @return Saved Employee entity if successful, otherwise throws an exception.
     */
    @Override
    @Transactional
    public Employee new_employee(Employee employee) {
        logger.info("Attempting to register a new employee: {}", employee.getName());

        // Fetch the hospital by ID
        Hospital hospital = hospitalRepository.findById(employee.getHospital().getId())
                .orElseThrow(() -> {
                    logger.error("Hospital not found with ID: {}", employee.getHospital().getId());
                    return new NoSuchElementException("HOSPITAL NOT FOUND ON THIS ID : " + employee.getHospital().getId());
                });

        // Check if the department exists in the given hospital
        boolean isDeptExistInHospital = hospital.getDepartments().stream()
                .anyMatch(dept -> dept.getId().equals(employee.getDepartment().getId()));

        if (isDeptExistInHospital) {
            logger.info("Department exists in hospital. Proceeding to save employee.");
            Employee savedEmployee = employeeRepository.save(employee);
            logger.info("Employee saved successfully with ID: {}", savedEmployee.getId());
            return savedEmployee;
        } else {
            logger.warn("Department ID {} not found in Hospital ID {}", employee.getDepartment().getId(), hospital.getId());
            throw new NoSuchElementException("DEPARTMENT NOT FOUND IN RESPECTIVE HOSPITAL");
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    

    /**
     * Registers a new doctor and associates them with a hospital and department.
     * @param details - DTO containing doctor and employee details.
     * @return Success message upon saving the doctor.
     */
    @Override
    @Transactional
    public String addDoctor(NewEmployeeDeptDTO details) {
        logger.info("Attempting to add a new doctor: {}", details.getName());

        // Fetch hospital and ensure the department exists within it
        Hospital hospital = hospitalRepository.findHospitalByIdAndDepartmentId(details.getHospitalId(), details.getDepartmentId())
                .orElseThrow(() -> {
                    logger.error("Invalid Hospital ID: {} or Department ID: {}", details.getHospitalId(), details.getDepartmentId());
                    return new IllegalArgumentException("INVALID HOSPITAL ID OR DEPARTMENT ID : DEPARTMENT ID NOT FOUND IN HOSPITAL");
                });

        // Create and save new employee
        Employee new_employee = new Employee();
        new_employee.setName(details.getName());
        new_employee.setDepartment(departmentRepository.findById(details.getDepartmentId()).orElse(null));
        new_employee.setHospital(hospital);
        new_employee.setSalary(details.getSalary());
        new_employee.setMobileNo(details.getMobileNo());
        
        Employee saved_emp = employeeRepository.save(new_employee);
        logger.info("New employee saved with ID: {}", saved_emp.getId());

        // Create and save new doctor
        Doctor new_doctor = new Doctor();
        new_doctor.setEmployeeId(saved_emp.getId());
        new_doctor.setPatients(null);
        new_doctor.setRole(details.getRole());
        new_doctor.setSpecialization(details.getSpecilization());
        new_doctor.setYearsOfExperience(details.getYearsOfExperience());

        doctorRepository.save(new_doctor);
        logger.info("New doctor saved for employee ID: {}", saved_emp.getId());

        return "NEW DOCTOR SAVED";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // fetching all employess
    @Transactional
    @Override
    public List<EmployeeRegisterDTO> getAllEmployees(){
    	
    	List<Employee> employeesList = employeeRepository.findAll();
    	EmployeeRegisterDTO employeeDTO;
    	List<EmployeeRegisterDTO> employeeDTOList = new ArrayList<EmployeeRegisterDTO>();
    	if(!employeesList.isEmpty()) {
    		for (Employee employee : employeesList) {
				employeeDTO = new EmployeeRegisterDTO();
				employeeDTO.setEmployeeId(employee.getId());
				employeeDTO.setName(employee.getName());
				employeeDTO.setMobileNo(employee.getMobileNo());
				employeeDTO.setAddress(employee.getAddress());
				employeeDTO.seteMailId(employee.geteMailId());
				employeeDTO.setDepartmentId(employee.getDepartment().getId());
				employeeDTO.setHospitalId(employee.getHospital().getId());
				employeeDTOList.add(employeeDTO);
			}
    	}
    	return employeeDTOList;
    }
    
    
    
    public List<EmployeeRegisterDTO> getEmployessByHospitalId(Long hospitalId){
    	System.out.println(hospitalId);
    	Hospital hospital = hospitalRepository.findById(hospitalId).orElse(null);
    	EmployeeRegisterDTO employeeDTO;
    	List<EmployeeRegisterDTO> employessDTOList = new ArrayList<EmployeeRegisterDTO>();
    	if(hospital != null) {
    		List<Employee> employessList =  employeeRepository.findEmployeesByHospitalId(hospitalId);
    		
    		for (Employee employee : employessList) {
    			employeeDTO = new EmployeeRegisterDTO();
    			employeeDTO.setEmployeeId(employee.getId());
    			employeeDTO.setHospitalId(employee.getHospital().getId());
    			employeeDTO.setName(employee.getName());
    			
    			employessDTOList.add(employeeDTO);
			}
    		
    		return employessDTOList;
    	}else {
    		return null;
    	}
    }
}
