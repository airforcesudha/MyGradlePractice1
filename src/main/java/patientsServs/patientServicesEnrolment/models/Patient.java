package patientsServs.patientServicesEnrolment.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "Table_Patient1188")
public class Patient {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Table_patientdisease1188", 
               joinColumns = @JoinColumn(name = "patient_id"), 
               inverseJoinColumns = @JoinColumn(name = "disease_id"))
    //@JsonBackReference
    private List<Disease> diseases = new ArrayList<>();

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

	public List<Disease> getDiseases() {
		return diseases;
	}

	public void setDiseases(List<Disease> diseases) {
		this.diseases = diseases;
	}
    
    
}
