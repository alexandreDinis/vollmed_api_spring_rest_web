package med.voll.api.dto;

import java.time.LocalDateTime;

public record DataAppointmentDTO(Long IdAppointment,Long IdDoctor, Long IdPatient, LocalDateTime date) {
}
