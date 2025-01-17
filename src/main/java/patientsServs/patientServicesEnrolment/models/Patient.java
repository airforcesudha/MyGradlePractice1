package patientsServs.patientServicesEnrolment.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;


/**
 * Entity class representing a Patient.
 * Mapped to the database table "Table_Patient_Details".
 */
@Entity
@Table(name = "Table_Patient_Details")
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

    /**
     * Many-to-Many relationship between Patient and Disease entities.
     * - This establishes a relationship where a patient can have multiple diseases,
     *   and a disease can affect multiple patients.
     * - The join table "Table_patientDisease_Details" is used to link these entities.
     * - Cascade types include:
     *   - PERSIST: Saves associated diseases when saving a patient.
     *   - MERGE: Updates associated diseases when updating a patient.
     *   - REMOVE: Deletes associated diseases when deleting a patient.
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(
        name = "Table_patientDisease_Details", // Name of the join table.
        joinColumns = @JoinColumn(name = "patient_id"), // Column in the join table referencing the patient.
        inverseJoinColumns = @JoinColumn(name = "disease_id") // Column in the join table referencing the disease.
    )
    private List<Disease> diseases = new ArrayList<>();

    // Getter for the ID.
    public Long getId() {
        return id;
    }

    // Setter for the ID.
    public void setId(Long id) {
        this.id = id;
    }

    // Getter for the name of the patient.
    public String getName() {
        return name;
    }

    // Setter for the name of the patient.
    public void setName(String name) {
        this.name = name;
    }

    // Getter for the age of the patient.
    public int getAge() {
        return age;
    }

    // Setter for the age of the patient.
    public void setAge(int age) {
        this.age = age;
    }

    // Getter for the date of birth of the patient.
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    // Setter for the date of birth of the patient.
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    // Getter for the list of diseases associated with the patient.
    public List<Disease> getDiseases() {
        return diseases;
    }

    // Setter for the list of diseases associated with the patient.
    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }
}