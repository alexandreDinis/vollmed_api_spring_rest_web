package med.voll.api.dto;

import med.voll.api.model.doctors.Doctor;
import med.voll.api.model.doctors.Especialidade;

public record DoctorsDTO(Long id, String nome, String crm, String email, String telefone, Especialidade especialidade) {

    public DoctorsDTO(Doctor dado) {
        this(dado.getId(), dado.getNome(), dado.getCrm(), dado.getEmail(), dado.getTelefone(), dado.getEspecialidade());
    }
}
