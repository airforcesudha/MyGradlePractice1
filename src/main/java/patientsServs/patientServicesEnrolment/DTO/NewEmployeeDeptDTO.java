package patientsServs.patientServicesEnrolment.DTO;



public class NewEmployeeDeptDTO {

	private Long id;

    private String name;
    
    private double salary;
    
    
    private String address;
    
    private String eMailId;
    
    private String mobileNo;
    
    private Long hospitalId;
    
    private Long departmentId;

	private String Specilization;
	
	private String Role;
	
	private Integer yearsOfExperience;
	
	// Default constructor
    public NewEmployeeDeptDTO() {
    }


	public NewEmployeeDeptDTO(Long id, String name, double salary, String address, String eMailId, String mobileNo,
			Long hospitalId, Long departmentId, String specilization, String role, Integer yearsOfExperience) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.address = address;
		this.eMailId = eMailId;
		this.mobileNo = mobileNo;
		this.hospitalId = hospitalId;
		this.departmentId = departmentId;
		Specilization = specilization;
		Role = role;
		this.yearsOfExperience = yearsOfExperience;
	}

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

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getSpecilization() {
		return Specilization;
	}

	public void setSpecilization(String specilization) {
		Specilization = specilization;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public Integer getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(Integer yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
	
	
	
	
}
