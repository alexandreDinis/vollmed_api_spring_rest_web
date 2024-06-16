package med.voll.api.model.patient;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.model.adress.AddressData;

public record PatientData(

        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        @Pattern(regexp = "\\d{11}", message = "CPF must be 11 digits")
        String cpf,

        @NotNull
        @Valid
        AddressData endereco) {
}
