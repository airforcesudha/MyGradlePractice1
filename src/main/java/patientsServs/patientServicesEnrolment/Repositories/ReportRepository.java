package patientsServs.patientServicesEnrolment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import patientsServs.patientServicesEnrolment.DTO.ReportDTO;
import patientsServs.patientServicesEnrolment.models.Report;


@Repository
public interface ReportRepository extends JpaRepository<Report,Long>{
	
	 @Query("SELECT new patientsServs.patientServicesEnrolment.DTO.ReportDTO(r.id, r.reportDate, r.description, r.reportType, r.createdBy, r.createdDate, r.updatedBy, r.updatedDate) " +
	           "FROM Report r WHERE r.patient.id = :patientId")
	  List<ReportDTO> findReportsByPatientId(@Param("patientId") Long patientId);

}
