package patientsServs.patientServicesEnrolment.serviceLayer;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import patientsServs.patientServicesEnrolment.DTO.DiseaseMedicineDTO;
import patientsServs.patientServicesEnrolment.DTO.PatientDTO;
import patientsServs.patientServicesEnrolment.DTO.ReportDTO;
import patientsServs.patientServicesEnrolment.models.Disease;
import patientsServs.patientServicesEnrolment.models.Doctor;
import patientsServs.patientServicesEnrolment.models.Hospital;
import patientsServs.patientServicesEnrolment.models.Patient;
import patientsServs.patientServicesEnrolment.models.Report;
import patientsServs.patientServicesEnrolment.repositories.DiseaseRepository;
import patientsServs.patientServicesEnrolment.repositories.DoctorRepository;
import patientsServs.patientServicesEnrolment.repositories.EmployeeRepository;
import patientsServs.patientServicesEnrolment.repositories.HospitalRepository;
import patientsServs.patientServicesEnrolment.repositories.MedicationRepository;
import patientsServs.patientServicesEnrolment.repositories.PatientRepository;
import patientsServs.patientServicesEnrolment.repositories.ReportRepository;
import patientsServs.patientServicesEnrolment.serviceIMP.PatientServiceIMP;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PatientsServices implements PatientServiceIMP {
    
    // Logger for the PatientsServices class
    private static final Logger logger = LoggerFactory.getLogger(PatientsServices.class);

    // Injecting required dependencies for this layer
    @Autowired
    PatientRepository patientRepository;
	
    @Autowired
    DiseaseRepository diseaseRepository;
	
    @Autowired
    MedicationRepository medicationRepository;
	
    @Autowired
    ReportRepository reportRepository;
	
    @Autowired
    private DoctorRepository doctorRepository;
	
    @Autowired
    private HospitalRepository hospitalRepository;
	
    @Autowired
    private EmployeeRepository employeeRepository;

    // Check if patient exists
    @Override
    public boolean isPatientExist(Long patientId) {    
        logger.info("Checking if patient with ID: {} exists", patientId);
        Patient patient = patientRepository.findById(patientId).orElse(null);
        if (patient == null) {
            logger.warn("Patient with ID: {} not found", patientId);
            return false;
        } else {
            logger.info("Patient with ID: {} found", patientId);
            return true;
        }
    }

    // Save patient
    @Override
    @Transactional
    public Patient savePatient(PatientDTO patientDetails) {
        logger.info("Saving patient: {}", patientDetails);
        
        // Retrieve hospital and doctor by their respective IDs
        Hospital hospital = hospitalRepository.findById(patientDetails.getHospitalId())
            .orElseThrow(() -> new NoSuchElementException("HOSPITAL NOT FOUND WITH GIVEN ID " + patientDetails.getHospitalId()));
        
        Doctor doctor = doctorRepository.findById(patientDetails.getDoctorId())
            .orElseThrow(() -> new NoSuchElementException("DOCTOR NOT FOUND WITH GIVEN ID"));
        
        // Check if doctor belongs to the same hospital as patient
        boolean isDoctorInSameHospital = employeeRepository.findById(doctor.getEmployeeId())
            .orElseThrow(() -> new NoSuchElementException("DOCTOR WAS NOT FOUND IN RESPECTIVE HOSPITAL ID: " + patientDetails.getHospitalId()))
            .getHospital().getId() == patientDetails.getHospitalId();
        
        if (isDoctorInSameHospital) {
            // Create a new patient object and set values
            Patient patient = new Patient();
            patient.setName(patientDetails.getName());
            patient.setDateOfBirth(patientDetails.getDateOfBirth());
            patient.setHospital(hospital);
            patient.getDoctors().add(doctor);
            doctor.getPatients().add(patient);
            
            // Save patient and return
            logger.info("Patient with ID: {} saved successfully", patient.getId());
            return patientRepository.save(patient);
        } else {
            throw new NoSuchElementException("DOCTOR NOT FOUND IN RESPECTIVE HOSPITAL- " + patientDetails.getHospitalId());
        }
    }

    // Add disease to a patient by patient id
    public Disease addDisease(Long patientId, DiseaseMedicineDTO diseaseDetails) {
        logger.info("Adding disease to patient with ID: {}", patientId);
        
        // Retrieve patient and add disease to them
        Patient patient = patientRepository.findById(patientId)
            .orElseThrow(() -> new NoSuchElementException("PATIENT WAS NOT FOUND WITH GIVEN ID: " + patientId));
        patient.getDiseases().add(diseaseDetails.getDisease());
        
        // Set medications for the disease and save it
        Disease disease = diseaseDetails.getDisease();
        disease.setMedications(diseaseDetails.getMedicines());
        logger.info("Disease added to patient with ID: {}", patientId);
        return diseaseRepository.save(disease);
    }

    // Get all patients
    @Override
    public List<Patient> getAllPatients() {
        logger.info("Fetching all patients");
        return patientRepository.findAll();
    }

    // Get patient by ID
    @Override
    public Patient getPatientById(Long ID) {
        logger.info("Fetching patient with ID: {}", ID);
        Patient patient = patientRepository.findById(ID).orElse(null);
        if (patient == null) {
            logger.warn("Patient with ID: {} not found", ID);
            return null;
        } else {
            logger.info("Patient with ID: {} found", ID);
            return patient;
        }
    }

    // Update patient by ID
    @Override
    public String updatePatient(Long id, Patient patient) {
        logger.info("Updating patient with ID: {}", id);
        if (getPatientById(id) == null) {
            logger.warn("Patient with ID: {} not found for update", id);
            return "Patient NOT FOUND";
        } else {
            patientRepository.save(patient);
            logger.info("Patient with ID: {} updated successfully", id);
            return "UPDATE SUCCESSFUL";
        }
    }

    // Delete patient by ID
    @Override
    public String deletePatient(Long id) {
        logger.info("Deleting patient with ID: {}", id);
        patientRepository.deleteById(id);
        logger.info("Patient with ID: {} deleted successfully", id);
        return "DELETED";
    }

    // Get registration details (total patients, diseases, medications)
    @Override
    public String RegistarationDetails() {
        logger.info("Fetching registration details");
        Integer totalPatients = patientRepository.findAll().size();
        Integer totalDisease = diseaseRepository.findAll().size();
        Integer totalMedicines = medicationRepository.findAll().size();
        
        logger.info("Total Patients: {}, Total Diseases: {}, Total Medications: {}", 
                    totalPatients, totalDisease, totalMedicines);
        
        return "TOTAL REGISTERED PATIENTS: " + totalPatients + "\n" 
             + "TOTAL REGISTERED DISEASES: " + totalDisease + "\n"
             + "TOTAL REGISTERED MEDICATIONS: " + totalMedicines;
    }

    // Get patients by Date of Birth (DOB) range
    @Override
    public List<Patient> byDOB(LocalDate startDate, LocalDate endDate) {
        logger.info("Fetching patients by DOB range: {} to {}", startDate, endDate);
        
        List<Patient> patientsList = patientRepository.findPatientsByDOBRange(startDate, endDate);
        
        if (patientsList.isEmpty()) {
            logger.warn("No patients found in the given DOB range: {} to {}", startDate, endDate);
        } else {
            logger.info("Found {} patients in the given DOB range", patientsList.size());
        }
        
        return patientsList;
    }

    // Get Patient by name
    @Override
    public List<Patient> getPatientByName(String patientName) {
        logger.info("Fetching patients by Name: {}", patientName);
        return patientRepository.findPatientsByName(patientName);
    }

    // Add patient report
    @Override
    public String addPatientReport(Report report) {
        logger.info("Adding patient report");
        reportRepository.save(report);
        return "saved";
    }

    // Fetching all patient reports based on patient id
    @Override
    public List<ReportDTO> patientReports(Long patientId) {
        logger.info("Fetching reports for patient with ID: {}", patientId);
        return reportRepository.findReportsByPatientId(patientId);
    }
}
