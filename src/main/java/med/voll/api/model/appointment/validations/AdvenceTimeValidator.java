package med.voll.api.model.appointment.validations;

import med.voll.api.infra.exception.ValidationException;
import med.voll.api.model.appointment.ScheduleData;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;


@Component
public class AdvenceTimeValidator implements IValidationScheduleAppointment{

    public void validate(ScheduleData data) {

        var scheduleDate = data.date();
        var now = LocalDateTime.now();
        var tolerance = Duration.between(now, scheduleDate).toMinutes();

        if(tolerance < 30) {
            throw new ValidationException("As consultas devem ser marcadas com ao menos 30 minutos de antecedencia!");
        }
    }
}
