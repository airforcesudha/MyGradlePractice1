package patientsServs.patientServicesEnrolment.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import patientsServs.patientServicesEnrolment.models.Disease;


@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long>{

}
