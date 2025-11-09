package med.voll.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String numero;
    private String complemento;

    public Endereco(DadosEndereco dto) {
        this.logradouro = dto.logradouro();
        this.cep = dto.cep();
        this.uf = dto.uf();
        this.bairro = dto.bairro();
        this.cidade = dto.cidade();
        this.numero = dto.numero();
        this.complemento = dto.complemento();
    }
}
