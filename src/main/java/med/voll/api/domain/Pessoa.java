package med.voll.api.domain;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.endereco.Endereco;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@MappedSuperclass
public abstract class Pessoa {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;

    @Embedded
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    public Pessoa(Long id, String nome, String email, String telefone, Perfil perfil, DadosEndereco endereco){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.perfil = perfil;
        this.endereco = new Endereco(endereco);
    }
}

