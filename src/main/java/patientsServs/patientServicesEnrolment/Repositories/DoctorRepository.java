package patientsServs.patientServicesEnrolment.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import patientsServs.patientServicesEnrolment.models.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{

}
