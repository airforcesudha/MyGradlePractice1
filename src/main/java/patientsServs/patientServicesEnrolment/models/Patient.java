package patientsServs.patientServicesEnrolment.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;



@Entity
@Table(name = "Patient_tbl")
public class Patient {

    // Primary key for the Patient entity, with auto-generated value.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Column representing the name of the patient.
    private String name;

    // Column representing the age of the patient.
    private int age;

    // Column representing the date of birth of the patient.
    private LocalDate dateOfBirth;

   
    
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "Patient_Disease_tbl", 
        joinColumns = @JoinColumn(name = "patient_id"),
        inverseJoinColumns = @JoinColumn(name = "disease_id")
    )
    private List<Disease> diseases = new ArrayList<>();

    
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "Patient_Medication_tbl", 
        joinColumns = @JoinColumn(name = "patient_id"),
        inverseJoinColumns = @JoinColumn(name = "medication_id")
    )
    private List<Medication> medications = new ArrayList<>();
    

   
    @ManyToMany(mappedBy = "patients")
    private List<Doctor> doctors = new ArrayList<>();



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



	public List<Disease> getDiseases() {
		return diseases;
	}



	public void setDiseases(List<Disease> diseases) {
		this.diseases = diseases;
	}



	public List<Medication> getMedications() {
		return medications;
	}



	public void setMedications(List<Medication> medications) {
		this.medications = medications;
	}



	public List<Doctor> getDoctors() {
		return doctors;
	}



	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}
    

	
    
    
    
}