package med.voll.api.model.doctors;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.voll.api.model.adress.AddressData;


public record DoctorsData(

        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull
        Especialidade especialidade,

        @NotNull
        @Valid
        AddressData endereco) {
}
