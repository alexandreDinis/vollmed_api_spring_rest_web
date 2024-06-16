package med.voll.api.service;

import med.voll.api.dto.DoctorsDTO;
import med.voll.api.dto.PatientDTO;
import med.voll.api.model.patient.PatientUpdate;
import med.voll.api.model.patient.Patient;
import med.voll.api.model.patient.PatientData;
import med.voll.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;

    public Patient registerPatient(PatientData patient) {

        return repository.save(new Patient(patient));
    }

    public Page<PatientDTO> listaDePacientes(Pageable paginacao) {
        return repository.findAllByStatusTrue(paginacao).map(p -> new PatientDTO(p.getId(), p.getNome(), p.getEmail(),p.getCpf()));

    }

    public Patient atualizarPaciente(PatientUpdate data) {

        var patient = repository.getReferenceById(data.id());

        patient.update(data);

        return patient;
    }

    public void delete(Long id) {
        var patient = repository.getReferenceById(id);
        patient.delete();
    }

    public PatientDTO detalhar (Long id) {

      var patient = repository.getReferenceById(id);

      var patientDTO = new PatientDTO(patient.getId(), patient.getNome(), patient.getEmail(),
              patient.getCpf());

      return patientDTO;
    }


}
