package med.voll.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

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

    public void update(DadosEndereco dto){
        Optional.ofNullable(dto.logradouro()).ifPresent(n -> this.logradouro = n);
        Optional.ofNullable(dto.bairro()).ifPresent(n -> this.bairro = n);
        Optional.ofNullable(dto.cep()).ifPresent(n -> this.cep = n);
        Optional.ofNullable(dto.cidade()).ifPresent(n -> this.cidade = n);
        Optional.ofNullable(dto.uf()).ifPresent(n -> this.logradouro = uf);
        Optional.ofNullable(dto.numero()).ifPresent(n -> this.numero = n);
        Optional.ofNullable(dto.complemento()).ifPresent(n -> this.complemento = n);

    }
}
