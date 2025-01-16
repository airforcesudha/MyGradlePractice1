package patientsServs.patientServicesEnrolment.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;




import jakarta.persistence.*;

@Entity
@Table(name = "Table_Patient_Details")
public class Patient {

	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    
    private int age;
    
    private LocalDate dateOfBirth;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Table_patientdisease_Details", 
               joinColumns = @JoinColumn(name = "patient_id"), 
               inverseJoinColumns = @JoinColumn(name = "disease_id"))
    
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

	
    
    
}
