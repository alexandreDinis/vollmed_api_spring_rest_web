package med.voll.api.model.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.model.doctors.Especialidade;

import java.time.LocalDateTime;

public record ScheduleData(

        Long idDoctor,

        @NotNull
        Long idPatient,

        @NotNull
        @Future
        LocalDateTime date,

        Especialidade especialidade
) {
}
