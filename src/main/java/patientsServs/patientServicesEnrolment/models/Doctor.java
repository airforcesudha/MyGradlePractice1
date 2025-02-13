package patientsServs.patientServicesEnrolment.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import patientsServs.patientServicesEnrolment.Auditable;

@Entity
@Table(name = "Doctors") // Defines the table name for the Doctor entity
public class Doctor extends Auditable {

    @Id // Specifies the primary key of the entity
    private Long employeeId; // Unique ID for the doctor, used as the primary key

    private String Specialization; // The specialization or field of expertise of the doctor
    
    private int yearsOfExperience; // The number of years of experience the doctor has

    private String role; // The role or position of the doctor (e.g., consultant, surgeon, etc.)

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }) // Many-to-many relationship with Patient
    @JoinTable(name = "doctorPatient", // The name of the join table between Doctor and Patient
               joinColumns = @JoinColumn(name = "doctorId"), // Column for doctor ID in the join table
               inverseJoinColumns = @JoinColumn(name = "patientId")) // Column for patient ID in the join table
    private List<Patient> patients = new ArrayList<>(); // List of patients that this doctor is associated with

    // Getters and setters for each field

    public String getSpecialization() {
        return Specialization; // Returns the specialization of the doctor
    }

    
    public void setSpecialization(String specialization) {
        Specialization = specialization; // Sets the specialization of the doctor
    }

    
    public List<Patient> getPatients() {
        return patients; // Returns the list of patients associated with this doctor
    }
    
    

    public void setPatients(List<Patient> patients) {
        this.patients = patients; // Sets the list of patients for this doctor
    }

    
    public int getYearsOfExperience() {
        return yearsOfExperience; // Returns the number of years of experience the doctor has
    }

    
    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience; // Sets the number of years of experience for the doctor
    }

    
    public String getRole() {
        return role; // Returns the role of the doctor
    }

    
    public void setRole(String role) {
        this.role = role; // Sets the role of the doctor
    }

    
    public Long getEmployeeId() {
        return employeeId; // Returns the employee ID of the doctor
    }

    
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId; // Sets the employee ID for the doctor
    }
}
