package patientsServs.patientServicesEnrolment.models;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Table_Disease_Details")
public class Disease {
	 	@Id
	 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;

	    @ManyToMany(mappedBy = "diseases")
	    @JsonBackReference
	    private List<Patient> patients = new ArrayList<>();

	    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
	    @JoinTable(name = "Table_diseaseMedication_Details",
	               joinColumns = @JoinColumn(name = "disease_id"),
	               inverseJoinColumns = @JoinColumn(name = "medication_id"))
	    private List<Medication> medications;

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

		public List<Patient> getPatients() {
			return patients;
		}

		public void setPatients(List<Patient> patients) {
			this.patients = patients;
		}

		public List<Medication> getMedications() {
			return medications;
		}

		public void setMedications(List<Medication> medications) {
			this.medications = medications;
		}
	    
	    
}

