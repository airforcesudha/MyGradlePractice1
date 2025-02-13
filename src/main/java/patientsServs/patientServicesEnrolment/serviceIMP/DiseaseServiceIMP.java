package patientsServs.patientServicesEnrolment.serviceIMP;

import java.util.List;

import patientsServs.patientServicesEnrolment.models.Disease;
import patientsServs.patientServicesEnrolment.models.Medication;

public interface DiseaseServiceIMP {

	Disease registerDisease(Disease disease, List<Medication> medications);

	List<Disease> getAllDiseases();

	Disease getDisease(Long diseaseId);

	List<Disease> getDiseasesByName(String diseaseName);

}
