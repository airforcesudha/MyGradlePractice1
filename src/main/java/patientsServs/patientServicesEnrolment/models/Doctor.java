package patientsServs.patientServicesEnrolment.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Doctors_tbl")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id") // Maps to Employee ID
	private Employee employee;

	private String Specialization;

	@ManyToOne
	@JoinColumn(name = "hospital_id", nullable = false)
	private Hospital hospital;

	@ManyToOne
	@JoinColumn(name = "department_id") // Relationship with Department
	private Department department;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
	@JoinTable(name = "doctor_patient_tbl", // Name of the join table
			joinColumns = @JoinColumn(name = "doctor_id"), // Column for doctor ID in the join table
			inverseJoinColumns = @JoinColumn(name = "patient_id") // Column for patient ID in the join table
	)
	private List<Patient> patients = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getSpecialization() {
		return Specialization;
	}

	public void setSpecialization(String specialization) {
		Specialization = specialization;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	
	
	

}
