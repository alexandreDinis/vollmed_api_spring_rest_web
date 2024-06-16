package med.voll.api.model.appointment.validations;

import med.voll.api.infra.exception.ValidationException;
import med.voll.api.model.appointment.ScheduleData;
import med.voll.api.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DoctorValidatorBusy implements IValidationScheduleAppointment {

    @Autowired
    private AppointmentRepository repository;

    public void validate(ScheduleData data) {

        var doctorBusy = repository.existsByDoctorIdAndDate(data.idDoctor(),data.date());
        if(doctorBusy) {
            throw new ValidationException("Medico ja possui uma consulta no mesmo horario!");
        }
    }
}
