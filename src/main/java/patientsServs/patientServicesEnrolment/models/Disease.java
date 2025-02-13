package patientsServs.patientServicesEnrolment.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import patientsServs.patientServicesEnrolment.Auditable;

@Entity
@Table(name = "Diseases") // Defines the table name for the Disease entity
public class Disease extends Auditable {

    @Id // Specifies the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID value
    private Long id;

    private String name; // Disease name

    private String description; // Disease description

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}) // Many-to-many relationship with Medication
    @JoinTable(name = "DiseaseMedication", // Join table to link diseases and medications
               joinColumns = @JoinColumn(name = "diseaseId"), // Column to link to the Disease entity
               inverseJoinColumns = @JoinColumn(name = "medicationId")) // Column to link to the Medication entity
    private List<Medication> medications; // List of medications associated with this disease

    @ManyToMany(mappedBy = "diseases") // Many-to-many relationship with Patient, mapped by 'diseases' in Patient
    @JsonBackReference // Prevents circular references in JSON serialization
    private List<Patient> patients = new ArrayList<>(); // List of patients who have this disease

    // Getters and setters for the fields

    public Long getId() {
        return id; // Returns the disease ID
    }

    public void setId(Long id) {
        this.id = id; // Sets the disease ID
    }

    public String getName() {
        return name; // Returns the disease name
    }

    public void setName(String name) {
        this.name = name; // Sets the disease name
    }

    public String getDescription() {
        return description; // Returns the disease description
    }

    public void setDescription(String description) {
        this.description = description; // Sets the disease description
    }

    public List<Patient> getPatients() {
        return patients; // Returns the list of patients with this disease
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients; // Sets the list of patients with this disease
    }

    public List<Medication> getMedications() {
        return medications; // Returns the list of medications for this disease
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications; // Sets the list of medications for this disease
    }
}
