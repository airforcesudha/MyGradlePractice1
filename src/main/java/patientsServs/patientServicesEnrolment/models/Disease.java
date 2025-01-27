package patientsServs.patientServicesEnrolment.models;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Disease_tbl")
public class Disease {
	
		
		
	 	@Id
	 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;
	    
	    private String description;

//	    @ManyToMany(mappedBy = "diseases")
//	    @JsonBackReference
//	    private List<Patient> patients = new ArrayList<>();
//
//	    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
//	    @JoinTable(name = "Disease_Medication_tbl",
//	               joinColumns = @JoinColumn(name = "disease_id"),
//	               inverseJoinColumns = @JoinColumn(name = "medication_id"))
//	    private List<Medication> medications;

	    @ManyToMany(mappedBy = "diseases")
	    private List<Patient> patients = new ArrayList<>();

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

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public List<Patient> getPatients() {
			return patients;
		}

		public void setPatients(List<Patient> patients) {
			this.patients = patients;
		}

		
	    
	    
}

