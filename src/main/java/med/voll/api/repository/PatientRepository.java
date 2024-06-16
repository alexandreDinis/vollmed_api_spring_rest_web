package med.voll.api.repository;

import med.voll.api.model.appointment.ScheduleData;
import med.voll.api.model.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Page<Patient> findAllByStatusTrue(Pageable paginacao);

    @Query("select p.status from Patient p where p.id = :id")
    Boolean findStautsById(Long id);

}
