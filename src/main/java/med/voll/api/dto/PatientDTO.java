package med.voll.api.dto;

import med.voll.api.model.patient.Patient;

public record PatientDTO(Long id, String nome, String email, String cpf) {

    public PatientDTO(Patient dado) {
        this(dado.getId(), dado.getNome(), dado.getEmail(),dado.getCpf());
    }
}
