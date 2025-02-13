package patientsServs.patientServicesEnrolment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PatientServicesEnrolmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientServicesEnrolmentApplication.class, args);
	}

}
