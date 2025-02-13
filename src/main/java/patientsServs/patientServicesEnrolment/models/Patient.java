package patientsServs.patientServicesEnrolment.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import patientsServs.patientServicesEnrolment.Auditable;



@Entity
@Table(name = "Patients")
public class Patient extends Auditable{

	
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

   
    
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
    @JoinTable(
        name = "PatientDisease", 
        joinColumns = @JoinColumn(name = "patientId"),
        inverseJoinColumns = @JoinColumn(name = "diseaseId")
    )
    private List<Disease> diseases = new ArrayList<>();
    
    

    
   
    @ManyToMany(mappedBy = "patients")
    private List<Doctor> doctors = new ArrayList<>();
    
    
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Report> reports = new ArrayList<>();
    
    
    @ManyToOne
    @JoinColumn(name = "hospitalId",referencedColumnName = "id")
    private Hospital hospital;


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




	public List<Doctor> getDoctors() {
		return doctors;
	}



	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}



	public List<Report> getReports() {
		return reports;
	}



	public void setReports(List<Report> reports) {
		this.reports = reports;
	}



	public Hospital getHospital() {
		return hospital;
	}



	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
	
	
	
    

}