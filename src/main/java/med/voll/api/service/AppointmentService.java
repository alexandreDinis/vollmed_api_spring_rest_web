package med.voll.api.service;


import med.voll.api.infra.exception.ValidationException;
import med.voll.api.model.appointment.Appointment;
import med.voll.api.model.appointment.ScheduleData;
import med.voll.api.model.appointment.validations.IValidationScheduleAppointment;
import med.voll.api.model.doctors.Doctor;
import med.voll.api.repository.AppointmentRepository;
import med.voll.api.repository.DoctorRepository;
import med.voll.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private List<IValidationScheduleAppointment> validates;


    public ScheduleData schedule (ScheduleData data) {

        if(!patientRepository.existsById(data.idPatient())){
            throw new ValidationException("Paciente não Cadastrado!");
        }

        if(data.idDoctor() != null && !doctorRepository.existsById(data.idDoctor())){
            throw new ValidationException("Medico não Cadastrado!");
        }

        validates.forEach(v -> v.validate(data));

        var patient = patientRepository.findById(data.idPatient())
                .orElseThrow(() -> new ValidationException("Paciente não Cadastrado!"));

        var doctor = doctorChoose(data);
        if(doctor == null) {
            throw new ValidationException("Não ha médico disponivel nessa dada!");
        }
        var appointment = new Appointment(null, doctor, patient, data.date());
        return new ScheduleData(appointment.getDoctor().getId(),appointment.getPatient().getId(), appointment.getDate(), appointment.getDoctor().getEspecialidade());

    }

    private Doctor doctorChoose(ScheduleData data) {

        if(data.idDoctor() != null) {

            return doctorRepository.getReferenceById(data.idDoctor());
        }

        if(data.especialidade() == null) {
            throw new ValidationException("A Especialidade não foi escolhida!");
        }

        return doctorRepository.escolherMedicoDisponivel(data.especialidade(), data.date());
    }
}
