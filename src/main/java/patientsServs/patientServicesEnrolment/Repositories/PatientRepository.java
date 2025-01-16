package patientsServs.patientServicesEnrolment.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import patientsServs.patientServicesEnrolment.models.Patient;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{

}
