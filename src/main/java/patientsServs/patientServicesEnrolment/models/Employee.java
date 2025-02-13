package patientsServs.patientServicesEnrolment.models;

import jakarta.persistence.*;
import patientsServs.patientServicesEnrolment.Auditable;

@Entity
@Table(name = "Employees")
public class Employee extends Auditable{
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    private double salary;
    
    
    private String address;
    
    private String eMailId;
    
    private String mobileNo;
    
    
    
    @ManyToOne
    @JoinColumn(name = "hospitalId") // Relationship with Hospital
    private Hospital hospital;

    
    @ManyToOne
    @JoinColumn(name = "departmentId") // Relationship with Department
    private Department department;


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


	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String geteMailId() {
		return eMailId;
	}


	public void seteMailId(String eMailId) {
		this.eMailId = eMailId;
	}


	public String getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
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
	
	
    
}
