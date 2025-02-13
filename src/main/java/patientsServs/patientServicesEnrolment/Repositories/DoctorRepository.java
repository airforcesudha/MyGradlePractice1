package patientsServs.patientServicesEnrolment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import patientsServs.patientServicesEnrolment.models.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{

}
