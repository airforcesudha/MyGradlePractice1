package patientsServs.patientServicesEnrolment.DTO;

import java.util.List;

public class AddDeptListRequest {
	
	private List<Long> depts_ids;

	public List<Long> getDepts_ids() {
		return depts_ids;
	}

	public void setDepts_ids(List<Long> depts_ids) {
		this.depts_ids = depts_ids;
	}

}
