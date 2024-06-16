package med.voll.api.model.doctors;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.model.adress.Address;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Boolean status;

    @Enumerated
    private Especialidade especialidade;

    @Embedded
    private Address endereco;

    public Doctor(DoctorsData data){
        this.status = true;
        this.nome = data.nome();
        this.crm = data.crm();
        this.telefone = data.telefone();
        this.email = data.email();
        this.especialidade = data.especialidade();
        this.endereco = new Address(data.endereco());
    }

    public void update(DoctorUpdate data) {
        if (data.nome() != null) {
            this.nome = data.nome();
        }
        if (data.telefone() != null) {
            this.telefone = data.telefone();
        }
        if (data.endereco() != null) {
            this.endereco.update(data.endereco());
        }
    }

    public void delete() {
        this.status = false;
    }
}
