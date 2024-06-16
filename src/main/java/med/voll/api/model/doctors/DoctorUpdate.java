package med.voll.api.model.doctors;

import jakarta.validation.constraints.NotNull;
import med.voll.api.model.adress.Address;
import med.voll.api.model.adress.AddressData;

public record DoctorUpdate(@NotNull Long id, String nome, String telefone, AddressData endereco) {
}
