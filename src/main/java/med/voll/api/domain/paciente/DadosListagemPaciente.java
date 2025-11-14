package med.voll.api.domain.paciente;

import med.voll.api.domain.medico.Especialidade;

public record DadosListagemPaciente(
        Long id,
        String nome,
        String email,
        String telefone,
        Convenio especialidade
) {
    public DadosListagemPaciente(Paciente p){
        this(p.getId(),p.getNome(),p.getEmail(),p.getTelefone(),p.getConvenio());
    }
}
