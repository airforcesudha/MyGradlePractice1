package patientsServs.patientServicesEnrolment.DTO;

public class LocationDTO {

	private Long id;
    private String state;
    private String city;
    private String country;
    private String pincode;
    
    public LocationDTO(Long id, String state, String city, String country, String pincode) {
        this.id = id;
        this.state = state;
        this.city = city;
        this.country = country;
        this.pincode = pincode;
    }

    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
    
    
}
