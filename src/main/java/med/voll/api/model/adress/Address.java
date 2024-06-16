package med.voll.api.model.adress;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String cep;
    private String uf;
    private String complemento;

    public Address(AddressData address) {
        this.logradouro = address.logradouro();
        this.numero = address.numero();
        this.bairro = address.bairro();
        this.cidade = address.cidade();
        this.cep = address.cep();
        this.uf = address.uf();
        this.complemento = address.complemento();
    }

    public void update(AddressData data) {

            if (data.logradouro() != null) {
                this.logradouro = data.logradouro();
            }
            if (data.numero() != null) {
                this.numero = data.numero();
            }
            if (data.bairro() != null) {
                this.bairro = data.bairro();
            }
            if (data.cidade() != null) {
                this.cidade = data.cidade();
            }
            if (data.cep() != null) {
                this.cep = data.cep();
            }
            if (data.uf() != null) {
                this.uf = data.uf();
            }
            if (data.complemento() != null) {
                this.complemento = data.complemento();
        }
    }
}
