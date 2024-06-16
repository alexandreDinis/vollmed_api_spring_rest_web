package med.voll.api.model.patient;

import med.voll.api.model.adress.AddressData;

public record PatientUpdate(Long id, String nome, String telefone, AddressData endereco) {
}
