package med.voll.api.model.appointment.validations;

import med.voll.api.infra.exception.ValidationException;
import med.voll.api.model.appointment.ScheduleData;
import med.voll.api.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PatientWithoutAppointments implements IValidationScheduleAppointment{

    @Autowired
    private AppointmentRepository repository;

    public void validate(ScheduleData data) {

        var patientWithoutAppointments = repository.existsByPatientIdAndDate(data.idPatient(), data.date());

        if(patientWithoutAppointments) {
            throw new ValidationException("Paciente já possue uma consulta nesse dia!");
        }
    }
}
