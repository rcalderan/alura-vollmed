package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.domain.Perfil;
import med.voll.api.domain.Pessoa;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.DadosUpdateMedico;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity()
@Table(name = "pacientes")
public class Paciente extends Pessoa {

    @Setter
    @Enumerated(EnumType.STRING)
    private Convenio convenio;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consulta> consultas = new ArrayList<>();


    public Paciente(Long id, @Valid DadosCadastroPaciente p) {
        super(id, p.nome(),p.email(),p.telefone(), Perfil.PACIENTE, p.endereco());
        this.convenio = p.convenio();
    }

    public void addConsulta(Consulta consulta) {
        var exist = this.consultas.stream()
                .filter(c -> c.getPaciente() == consulta.getPaciente())
                .limit(1)
                .toList();
        if(exist.isEmpty()){
            this.consultas.add(consulta);
        }
    }

    public void update(@Valid DadosUpdatePaciente dto) {
        Optional.ofNullable(dto.nome()).ifPresent(this::setNome);
        Optional.ofNullable(dto.telefone()).ifPresent(this::setTelefone);
        Optional.ofNullable(dto.email()).ifPresent(this::setEmail);
        Optional.ofNullable(dto.perfil()).ifPresent(this::setPerfil);
        Optional.ofNullable(dto.convenio()).ifPresent(this::setConvenio);
        Optional.ofNullable(dto.endereco()).ifPresent(end -> this.setEndereco(new Endereco(end)));
    }
}

