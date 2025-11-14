package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.Pessoa;
import med.voll.api.domain.consulta.Consulta;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "pacientes")
@Table(name = "Pacientes")
public class Paciente extends Pessoa {

    @Enumerated(EnumType.STRING)
    private Convenio convenio;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consulta> consultas = new ArrayList<>();


    public Paciente(@Valid DadosCadastroPaciente p) {
        super( p.nome(),p.email(),p.telefone(), p.endereco());
        this.convenio = p.convenio();
    }
}

