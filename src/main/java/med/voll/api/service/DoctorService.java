package med.voll.api.service;

import med.voll.api.dto.DoctorsDTO;
import med.voll.api.model.doctors.Doctor;
import med.voll.api.model.doctors.DoctorUpdate;
import med.voll.api.model.doctors.DoctorsData;
import med.voll.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repository;

    public Doctor registerDoctors(DoctorsData data) {

        return repository.save(new Doctor(data));
    }

    public Page<DoctorsDTO> listaDeMedicos(Pageable paginacao) {
        return repository.findAllByStatusTrue(paginacao).map(d -> new DoctorsDTO(d.getId(), d.getNome(),
                d.getCrm(), d.getEmail(), d.getTelefone(), d.getEspecialidade()));
    }

    public List<DoctorsDTO> converteDados(List<Doctor> doctors) {
        return doctors.stream()
                .map(d -> new DoctorsDTO(d.getId(), d.getNome(), d.getCrm(), d.getEmail(),
                        d.getTelefone(), d.getEspecialidade()))
                .toList();
    }

    public Doctor atualizarMedico(DoctorUpdate data) {
        var doctor = repository.getReferenceById(data.id());
        doctor.update(data);

        return doctor;
    }

    public void delete(Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.delete();
    }

    public DoctorsDTO detalhar (Long id) {

        var doctor = repository.getReferenceById(id);

        var doctorDTO = new DoctorsDTO(doctor.getId(), doctor.getNome(), doctor.getEmail(),
                doctor.getCrm(), doctor.getTelefone(),doctor.getEspecialidade());

        return doctorDTO;
    }
}
