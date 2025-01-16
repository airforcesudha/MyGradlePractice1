package patientsServs.patientServicesEnrolment.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import patientsServs.patientServicesEnrolment.models.Medication;


@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long>{

}
