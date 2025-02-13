package patientsServs.patientServicesEnrolment.DTO;

import java.util.List;

import patientsServs.patientServicesEnrolment.models.Disease;
import patientsServs.patientServicesEnrolment.models.Medication;

public class DiseaseMedicineDTO {
	
	
	private Disease disease;
	
	private List<Medication> medicines;
	
	

	public DiseaseMedicineDTO() {
		super();
	}



	public Disease getDisease() {
		return disease;
	}



	public void setDisease(Disease disease) {
		this.disease = disease;
	}



	public List<Medication> getMedicines() {
		return medicines;
	}



	public void setMedicines(List<Medication> medicines) {
		this.medicines = medicines;
	}



	public DiseaseMedicineDTO(Disease disease, List<Medication> medicines) {
		super();
		this.disease = disease;
		this.medicines = medicines;
	}
	
	

}
