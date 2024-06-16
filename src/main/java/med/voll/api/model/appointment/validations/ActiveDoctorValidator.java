package med.voll.api.model.appointment.validations;

import med.voll.api.infra.exception.ValidationException;
import med.voll.api.model.appointment.ScheduleData;
import med.voll.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ActiveDoctorValidator implements IValidationScheduleAppointment {

    @Autowired
    private DoctorRepository repository;

    public void validate(ScheduleData data) {

        if(data.idDoctor() == null) {
            return;
        }

        var activateDoctor = repository.findStatusById(data.idDoctor());

        if(!activateDoctor) {

            throw new ValidationException("Medico não esta ativo!");
        }

    }
}
