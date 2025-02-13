package patientsServs.patientServicesEnrolment.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import patientsServs.patientServicesEnrolment.Auditable;

@Entity
@Table(name = "Departments") // Defines the table name for the Department entity
public class Department extends Auditable {

    @Id // Specifies the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID value
    private Long id;

    private String name; // Department name

    @ManyToMany(mappedBy = "departments") // Many-to-many relationship with Hospital
    private List<Hospital> hospitals = new ArrayList<>(); // List of hospitals associated with this department

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL) // One-to-many relationship with Employee
    private List<Employee> employees = new ArrayList<>(); // List of employees working in this department

    // Getters and setters for the fields

    public Long getId() {
        return id; // Returns the department ID
    }

    public void setId(Long id) {
        this.id = id; // Sets the department ID
    }

    public String getName() {
        return name; // Returns the department name
    }

    public void setName(String name) {
        this.name = name; // Sets the department name
    }

    public List<Hospital> getHospitals() {
        return hospitals; // Returns the list of hospitals associated with the department
    }

    public void setHospitals(List<Hospital> hospitals) {
        this.hospitals = hospitals; // Sets the list of hospitals associated with the department
    }

    public List<Employee> getEmployees() {
        return employees; // Returns the list of employees working in this department
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees; // Sets the list of employees working in the department
    }

    // The following commented-out code is for auditing fields, typically used for tracking
    // entity creation and modification times, and who made those changes.
    
//    @Column(name = "createdBy", updatable = false) 
//    private String createdBy; // Stores the user who created the department

//    @Column(name = "createdDate", updatable = false)
//    private LocalDateTime createdDate; // Stores the creation timestamp

//    @Column(name = "updatedBy")
//    private String updatedBy; // Stores the user who last updated the department

//    @Column(name = "updatedDate")
//    private LocalDateTime updatedDate; // Stores the last updated timestamp

//    @PrePersist
//    public void prePersist() {
//        this.createdDate = LocalDateTime.now(); // Sets the creation timestamp
//        this.createdBy = "admin"; // Sets the createdBy to "admin" (replace with actual user info)
//    }

//    @PreUpdate
//    public void preUpdate() {
//        this.updatedDate = LocalDateTime.now(); // Sets the updated timestamp
//        this.updatedBy = "admin"; // Sets the updatedBy to "admin" (replace with actual user info)
//    }

}
