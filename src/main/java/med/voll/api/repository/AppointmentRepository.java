package med.voll.api.repository;

import med.voll.api.model.appointment.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Boolean existsByDoctorIdAndDate(Long idDoctor, LocalDateTime date);

    Boolean existsByPatientIdAndDate(Long idPatient, LocalDateTime date);
}
