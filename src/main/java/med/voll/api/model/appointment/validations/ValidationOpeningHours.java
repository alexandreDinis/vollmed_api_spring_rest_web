package med.voll.api.model.appointment.validations;

import med.voll.api.infra.exception.ValidationException;
import med.voll.api.model.appointment.ScheduleData;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;


@Component
public class ValidationOpeningHours implements IValidationScheduleAppointment{


    public void validate(ScheduleData data) {

        var scheduleDate = data.date();
        var sunday = scheduleDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeHour = scheduleDate.getHour() < 7;
        var afterHours = scheduleDate.getHour() > 18;

        if(sunday || beforeHour || afterHours) {
            throw new ValidationException("Fora do horario de expediente da clinica!");
        }
    }
}
