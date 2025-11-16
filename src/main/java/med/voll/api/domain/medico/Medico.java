package med.voll.api.domain.medico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.endereco.Endereco;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Entity(name = "medicos")
@Table(name="Medicos")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consulta> consultas = new ArrayList<>();

    @Embedded
    private Endereco endereco;

    public Medico(DadosCadastroMedico medicoDTO) {
        this.nome =medicoDTO.nome();
        this.crm = medicoDTO.crm();
        this.email = medicoDTO.email();
        this.telefone = medicoDTO.telefone();
        this.especialidade = medicoDTO.especialidade();
        this.endereco = new Endereco(medicoDTO.endereco());
    }

    public void addConsulta(Consulta consulta) {
        if(consulta == null){
            return;
        }
        var exist = this.consultas.stream()
                .filter(c -> c.getMedico() == consulta.getMedico())
                .limit(1)
                .toList();
        if(exist.isEmpty()){
            this.consultas.add(consulta);
        }
    }

    public void update(@Valid DadosUpdateMedico dto) {
        Optional.ofNullable(dto.nome()).ifPresent(n -> this.nome = n);
        Optional.ofNullable(dto.telefone()).ifPresent(n -> this.telefone = n);
        Optional.ofNullable(dto.email()).ifPresent(e -> this.email = e);
        Optional.ofNullable(dto.crm()).ifPresent(c -> this.crm = c);
        Optional.ofNullable(dto.especialidade()).ifPresent(es -> this.especialidade = es);
        Optional.ofNullable(dto.endereco()).ifPresent(end -> this.endereco.update(end));
    }
}
