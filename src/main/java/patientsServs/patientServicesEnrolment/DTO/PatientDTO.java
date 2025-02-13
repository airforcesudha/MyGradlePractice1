package patientsServs.patientServicesEnrolment.DTO;

import java.time.LocalDate;

public class PatientDTO {
	
	private Long id;               // Primary key for the Patient entity
    private String name;           // Name of the patient
    private int age;               // Age of the patient
    private LocalDate dateOfBirth; // Date of birth of the patient
    private Long hospitalId;       // ID of the hospital
    private Long doctorId;
    
    
    
	public PatientDTO() {
		super();
	}



	public PatientDTO(Long id, String name, int age, LocalDate dateOfBirth, Long hospitalId, Long doctorId) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.dateOfBirth = dateOfBirth;
		this.hospitalId = hospitalId;
		this.doctorId = doctorId;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}



	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}



	public Long getHospitalId() {
		return hospitalId;
	}



	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}



	public Long getDoctorId() {
		return doctorId;
	}



	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}    
    
    
    

}
