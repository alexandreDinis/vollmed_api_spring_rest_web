package med.voll.api.model.appointment.validations;

import med.voll.api.infra.exception.ValidationException;
import med.voll.api.model.appointment.ScheduleData;
import med.voll.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ActivePatientValidation implements IValidationScheduleAppointment{

    @Autowired
    private PatientRepository repository;

    public void validate(ScheduleData data) {

        var activateDoctor = repository.findStautsById(data.idPatient());

        if(!activateDoctor) {
            throw new ValidationException("Paciente não esta ativo!");

        }
    }
}
