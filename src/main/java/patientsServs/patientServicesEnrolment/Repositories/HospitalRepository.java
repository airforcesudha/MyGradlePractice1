package patientsServs.patientServicesEnrolment.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import patientsServs.patientServicesEnrolment.models.Hospital;


@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long>{

}
