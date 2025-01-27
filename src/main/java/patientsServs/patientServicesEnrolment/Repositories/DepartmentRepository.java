package patientsServs.patientServicesEnrolment.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import patientsServs.patientServicesEnrolment.models.Department;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{


}
